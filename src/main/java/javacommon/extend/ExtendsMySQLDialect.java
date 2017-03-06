package javacommon.extend;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

/**
 * @author zdf
 * @version 1.1
 * @date May 26, 2014 5:31:45 PM
 */
public class ExtendsMySQLDialect extends MySQLDialect {
	public ExtendsMySQLDialect() {
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}
}
