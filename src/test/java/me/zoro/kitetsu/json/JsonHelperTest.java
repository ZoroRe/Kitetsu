package me.zoro.kitetsu.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author luguanquan
 * @date 2020-03-12 23:01
 */
@DisplayName("JsonHelper测试")
public class JsonHelperTest {


	@DisplayName("fromJson测试")
	@Test
	public void fromJsonTest() {
		String outer1Str = "{\"data\":{\"id\":\"12345\"}}";
		String outer2Str = "{\"data\":{}}";
		Outer outer1 = JsonHelper.fromJson(outer1Str, Outer.class);
		Outer outer2 = JsonHelper.fromJson(outer2Str, Outer.class);
		assertNotNull(outer1);
		assertNotNull(outer2);
		assertNotNull(outer1.getData());
		assertNotNull(outer2.getData());
		assertEquals("12345", outer1.getData().getId());
		assertNull(outer2.getData().getId());

		String json1 = JsonHelper.toJson(outer1);
		assertEquals(json1, outer1Str);
		String prettyJson = JsonHelper.toPrettyJson(json1);

		String json2 = JsonHelper.toJson(outer2);
		assertEquals(json2, outer2Str);

	}

	@DisplayName("toJson测试")
	@Test
	public void toJsonTest(){
		String outer1Str = "{\"data\":{\"id\":\"12345\"}}";
		Outer outer1 = new Outer();
		Data data1 = new Data();
		data1.setId("12345");
		outer1.setData(data1);
		assertEquals(outer1Str, JsonHelper.toJson(outer1));

		String outer2Str = "{\"data\":{}}";
		Outer outer2 = new Outer();
		Data data2 = new Data();
		outer2.setData(data2);
		assertEquals(outer2Str, JsonHelper.toJson(outer2));

	}

	public static class Outer {
		private Data data;

		public Data getData() {
			return data;
		}

		public void setData(Data data) {
			this.data = data;
		}
	}

	public static class Data {
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}
}
