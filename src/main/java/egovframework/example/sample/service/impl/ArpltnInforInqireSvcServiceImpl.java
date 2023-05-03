package egovframework.example.sample.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import egovframework.example.sample.service.ArpltnInforInqireSvcService;

@Service
public class ArpltnInforInqireSvcServiceImpl implements ArpltnInforInqireSvcService {
	private static final Logger log = LoggerFactory.getLogger(ArpltnInforInqireSvcServiceImpl.class);
	
	@Value("${data.arpltnInforInqireSvc.serviceUrl}")
	private String arpltnInforInqireSvcUrl;
	
	@Value("${data.arpltnInforInqireSvc.serviceKey}")
	private String serviceKey;
	
	@Override
	public Map<String, Object> getMinuDustFrcstDspth() {
//		return Lists.newArrayList();
		try {
			log.info("arpltnInforInqireSvcUrl: {}", arpltnInforInqireSvcUrl);

			String url = UriComponentsBuilder.fromHttpUrl(arpltnInforInqireSvcUrl)
				.path("/getMinuDustFrcstDspth")
				.queryParam("serviceKey", serviceKey)
				.queryParam("returnType", "json")
				.queryParam("numOfRows", 1000)
				.queryParam("pageNo", 1)
				.build()
				.encode()
				.toUriString();
			
			log.info("url: {}", url);

			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
			
            log.info("Response code: {}", conn.getResponseCode());
            
            StringWriter result = new StringWriter();
            IOUtils.copy(conn.getInputStream(), result, Charset.defaultCharset());
            
            log.info("result: {}", StringUtils.substring(result.toString(), 0, 100));
            
            log.info("Response code: {}", conn.getResponseCode());
            
            conn.disconnect();
            
//			return toMap("[\r\n" + 
//					"  {\r\n" + 
//					"    \"name1\": \"username.1\",\r\n" + 
//					"    \"name2\": \"username.2\",\r\n" + 
//					"    \"name3\": \"username.3\"\r\n" + 
//					"  },\r\n" + 
//					"  {\r\n" + 
//					"    \"name1\": \"username.1\",\r\n" + 
//					"    \"name2\": \"username.2\",\r\n" + 
//					"    \"name3\": \"username.3\"\r\n" + 
//					"  }\r\n" + 
//					"]");
            return toMap(result.toString());
		} catch (IOException e ) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	
	private Map<String, Object> toMap(String value) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		try {
			return mapper.readValue(value, new TypeReference<Map<String, Object>>() {});
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			return Maps.newHashMap();
		}
	}
	
}
