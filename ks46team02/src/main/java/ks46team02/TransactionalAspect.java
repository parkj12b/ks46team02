package ks46team02;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Configuration
public class TransactionalAspect {
	
	private static final String AOP_TRANSACTION_METHOD_NAME = "*";
	private static final String AOP_TRANSACTION_EXPRESSION = "execution(* ks46team02..service.*Service.*(..))";
	
	private final TransactionManager transactionManager;
	
	public TransactionalAspect(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}
	
	@Bean 
	public TransactionInterceptor transactionAdvice() {
		MatchAlwaysTransactionAttributeSource source = 
				new MatchAlwaysTransactionAttributeSource();
		RuleBasedTransactionAttribute transactionAttribute = 
				new RuleBasedTransactionAttribute();
		transactionAttribute.setName(AOP_TRANSACTION_METHOD_NAME);
		transactionAttribute.setRollbackRules(Collections.singletonList(
				new RollbackRuleAttribute(Exception.class)));
		
		return new TransactionInterceptor(transactionManager, source);
	}
	
	@Bean
	public Advisor transactionAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_TRANSACTION_EXPRESSION);
		
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}
