package mail;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.mail.Session;

import org.junit.Test;

/**
 * @author Xudong.Liu
 *
 */
public class a01_SendMail {

	@Test
	public void testSendMail() throws Exception {
		Properties props = new Properties();
		Session.getDefaultInstance(props);
	}
}
