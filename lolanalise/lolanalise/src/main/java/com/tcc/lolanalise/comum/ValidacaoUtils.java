package com.tcc.lolanalise.comum;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Predicate;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ValidacaoUtils {
	static Logger logger = LoggerFactory.getLogger(ValidacaoUtils.class);

	static <V> void throwIf(V value, Predicate<V> predicate) throws ServiceException {
		throwIf(ServiceException.class, value, predicate, null);
	}

	static <V> void throwIf(V value, Predicate<V> predicate, String mensagem) throws ServiceException {
		throwIf(ServiceException.class, value, predicate, mensagem);
	}

	static <V> void throwIf(Class<? extends ServiceException> exceptionClass, V value, Predicate<V> predicate)
			throws ServiceException {
		throwIf(exceptionClass, value, predicate, null);
	}

	static <V> void throwIf(Class<? extends ServiceException> exceptionClass, V value, Predicate<V> predicate,
                            String mensagem) throws ServiceException {
		if (predicate.test(value)) {
			try {
				Constructor<? extends ServiceException> contructor = null;
				Object[] params = new Object[] { mensagem, value };
				try {
					contructor = exceptionClass.getConstructor(String.class, value.getClass());
				} catch (NoSuchMethodException | SecurityException | NullPointerException e) {
					logger.warn("Não foi possível encontrar um construtor - {}", e.getMessage());
					contructor = exceptionClass.getConstructor(String.class);
					params = new Object[] { mensagem };
				}
				logger.debug("lançando exceção {} ", contructor);
				throw contructor.newInstance(params);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				logger.error("Erro ao lançar exceção", e);
			}
		}
	}
}
