package com.example.demo.statemachine;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.OnTransitionStart;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
		// 设置状态机的初试状态
		states.withStates().initial(States.UNPAID).states(EnumSet.allOf(States.class));
	}

	@Override
	public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
		// 定义事件对状态变更的影响
		transitions
			// 定义Events.PAY事件将使状态从States.UNPAID变更为States.WAITING_FOR_RECEIVE
			.withExternal().source(States.UNPAID).target(States.WAITING_FOR_RECEIVE).event(Events.PAY)
			.and()
			// 定义Events.RECEIVE事件将使状态从States.WAITING_FOR_RECEIVE变更为States.DONE
			.withExternal().source(States.WAITING_FOR_RECEIVE).target(States.DONE).event(Events.RECEIVE);
	}

	/*
	 * //可以在该处设置监听器 //也可以用@WithStateMachine、@OnTransition、@OnTransitionStart、@
	 * OnTransitionEnd注释在单独的类中定义监听器实现，如EventConfig.java
	 * 
	 * @Override public void configure(StateMachineConfigurationConfigurer<States,
	 * Events> config) throws Exception {
	 * config.withConfiguration().listener(listener()); }
	 * 
	 * @Bean public StateMachineListener<States, Events> listener() { return new
	 * StateMachineListenerAdapter<States, Events>() {
	 * 
	 * @Override public void transition(Transition<States, Events> transition) { if
	 * (transition.getTarget().getId() == States.UNPAID) { logger.info("订单创建，待支付");
	 * return; }
	 * 
	 * if (transition.getSource().getId() == States.UNPAID &&
	 * transition.getTarget().getId() == States.WAITING_FOR_RECEIVE) {
	 * logger.info("用户完成支付，待收货"); return; }
	 * 
	 * if (transition.getSource().getId() == States.WAITING_FOR_RECEIVE &&
	 * transition.getTarget().getId() == States.DONE) { logger.info("用户已收货，订单完成");
	 * return; } }
	 * 
	 * }; }
	 */

}
