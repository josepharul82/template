package fr.society.template.application.aop;

import fr.society.template.application.config.properties.UsersProperties;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Log
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoggingAspect {

	UsersProperties usersProperties;

	public LoggingAspect(UsersProperties usersProperties) {
		this.usersProperties = usersProperties;
	}

	@Pointcut("execution(public * fr.society.template.application.web.*.*(..))")
	public void controllerMethodTime() {
		//See spring documentation
	}

	@Pointcut("execution(public * fr.society.template.service.impl.*.*(..))")
	public void serviceMethodTime() {
		//See spring documentation
	}

	@Pointcut("execution(public * fr.society.template.repository.*.*(..))")
	public void repositoryMethodTime() {
		//See spring documentation
	}
	
	@Around("controllerMethodTime() || serviceMethodTime() || repositoryMethodTime()")
	public Object beforeSampleCreation(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object output = proceedingJoinPoint.proceed();
		stopWatch.stop();
		locExecutionTime(proceedingJoinPoint, stopWatch);
		return output;
	}

	private void locExecutionTime(ProceedingJoinPoint proceedingJoinPoint, StopWatch stopWatch) {
		String message = composeLogMessage(proceedingJoinPoint, stopWatch);
		if(stopWatch.getTime() > this.usersProperties.getLogging().getAlertWarningMethodExecutionTime()){
			log.warning(message);
		}
	}

	private String composeLogMessage(ProceedingJoinPoint proceedingJoinPoint, StopWatch stopWatch) {
		Object[] args = proceedingJoinPoint.getArgs();
		StringBuilder logMessage = new StringBuilder();
		logMessage.append(proceedingJoinPoint.getTarget().getClass().getName());
		logMessage.append(".");
		logMessage.append(proceedingJoinPoint.getSignature().getName());
		logMessage.append("(");
		Arrays.asList(args).stream().forEach(arg -> logMessage.append(arg).append(","));
		logMessage.deleteCharAt(logMessage.toString().length()-1);
		logMessage.append(")");
		logMessage.append(" execution time: ");
		logMessage.append(stopWatch.getTime());
		logMessage.append(" ms");
		return logMessage.toString();
	}
}
