package com.example.demo;

import com.example.demo.converter.MoneyReadConverter;
import com.example.demo.mapper.CoffeeMapper;
import com.example.demo.model.*;
import com.example.demo.service.CoffeeService;
import com.example.demo.service.OrderService;
import com.github.pagehelper.PageInfo;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@SpringBootApplication
@Slf4j
@MapperScan("com.example.demo.mapper")
public class DemoApplication implements ApplicationRunner {

	@Autowired
	private CoffeeService coffeeService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CoffeeMapper coffeeMapper;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Bean
	public MongoCustomConversions mongoCustomConversions(){
		return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		//generateArtifacts();
		//playWithArtifacts();
		//playWithPageHelper();
		//playWithMyBatis();
		playWithMongodb();
	}

	/**
	 * mybatis自动生成方法
	 * @throws Exception
	 */
	private void generateArtifacts() throws Exception {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(
				this.getClass().getResourceAsStream("/generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
	private void playWithArtifacts() {
		Coffee espresso = new Coffee()
				.withName("espresso")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date());
		coffeeMapper.insert(espresso);
		log.info("Coffee:{}",espresso);
		Coffee latte = new Coffee()
				.withName("latte")
				.withPrice(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.withCreateTime(new Date())
				.withUpdateTime(new Date());
		coffeeMapper.insert(latte);
		log.info("Coffee:{}",latte);
		Coffee s = coffeeMapper.selectByPrimaryKey(1L);
		log.info("Coffee {}", s);

		CoffeeExample example = new CoffeeExample();
		example.createCriteria().andNameEqualTo("latte");
		List<Coffee> list = coffeeMapper.selectByExample(example);
		//list.forEach(e -> log.info("selectByExample: {}", e));
		list.forEach(coffee -> log.info("selectByExample: {}", coffee));
	}

	private void playWithPageHelper(){

		coffeeMapper.findAllWithRowBounds(new RowBounds(1,3))
		.forEach(coffee -> log.info("Page(1) Coffee{}",coffee));
		coffeeMapper.findAllWithRowBounds(new RowBounds(2,3))
		.forEach(coffee -> log.info("Page(2) Coffee{}",coffee));

		log.info("分割线{}","============");

		coffeeMapper.findAllWithRowBounds(new RowBounds(1,0))
		.forEach(coffee -> log.info("Page(1) Coffee{}",coffee));

		log.info("================");

		coffeeMapper.findAllWithParam(1,3)
		.forEach(coffee -> log.info("Page(1) Coffee{}",coffee));
		List<Coffee>list = coffeeMapper.findAllWithParam(2,3);

		PageInfo<Coffee>info = new PageInfo<>(list);
		log.info("PageInfo:{}",info);
	}
	private void playWithMyBatis(){
		log.info("All Coffee:{}",coffeeMapper.selectByExample(new CoffeeExample()));
		List<Coffee>coffeeList =  coffeeService.findOneCoffeeByName("Latte");
		if(null != coffeeList){
			CoffeeOrder order =
					orderService.createOrder("Li Lei",coffeeList.get(0),coffeeList.get(1));
			log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
			log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
		}
	}
	private void playWithMongodb() throws InterruptedException {
		//提交测试
		MongoCoffee latte = MongoCoffee.builder()
				.name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"),20.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();
		MongoCoffee coffee = mongoTemplate.save(latte);
		log.info("MongoCoffee:{}",coffee);
		List<MongoCoffee>list = mongoTemplate.find(
				Query.query(Criteria.where("name").is("latte")),MongoCoffee.class);
		log.info("Find {} MongoCoffee",list.size());
		list.forEach(mongoCoffee -> log.info("MongoCoffee {}",mongoCoffee));

			Thread.sleep(1000);//为了看更新时间
			UpdateResult result = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("latte")),
					new Update().set("price",Money.ofMajor(CurrencyUnit.of("CNY"),30))
							.currentDate("updateTime"),
					MongoCoffee.class);
			log.info("Update Result:{}",result.getModifiedCount());
			MongoCoffee updateOne = mongoTemplate.findById(coffee.getId(),MongoCoffee.class);
			log.info("Update Result:{}",updateOne);
			mongoTemplate.remove(updateOne);

	}
}