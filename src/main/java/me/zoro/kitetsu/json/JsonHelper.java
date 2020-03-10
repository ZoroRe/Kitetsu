package me.zoro.kitetsu.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author luguanquan
 * @date 2020-03-10 21:44
 */
public class JsonHelper {
	private static final Gson gson = new Gson();
private static final Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();

	public static String toJson(Object src) {
		if (src == null){
			return null;
		}
		return gson.toJson(src);
	}

	public static String toJson(Object src, Type typeOfSrc) {
		if (src == null){
			return null;
		}
		return gson.toJson(src, typeOfSrc);
	}

	public static String toPrettyJson(Object src) {
		if (src == null){
			return null;
		}
		return gsonPretty.toJson(src);
	}

	public static String toPrettyJson(String src) {
		if (src == null) {
			return null;
		}
		JsonObject object = gson.fromJson(src, JsonObject.class);
		return gsonPretty.toJson(object);
	}

	public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
		if (json == null) {
			return null;
		}
		return gson.fromJson(json, typeOfT);
	}

	public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
		if (json == null) {

			return null;
		}
		return gson.fromJson(json, classOfT);
	}

	public static <T> T fromJson(String json, Class<T> rawClass, Type... args) throws JsonSyntaxException {
		if (json == null) {
			return null;
		}
		Type type = getType(rawClass, args);
		return gson.fromJson(json, type);
	}

	/**
	 * 获取泛型类型，例如获取Map<String,Integer>的类型
	 *
	 * @param rawClass 泛型主类，例如Map<String,Integer>中的Map.class
	 * @param args     泛型参数，例如Map<String,Integer>中的String.class和Integer.class
	 * @return
	 */
	public static ParameterizedType getType(final Class rawClass, final Type... args) {
		return new ParameterizedType() {
			@Override
			public Type[] getActualTypeArguments() {
				return args;
			}

			@Override
			public Type getRawType() {
				return rawClass;
			}

			@Override
			public Type getOwnerType() {
				return null;
			}
		};
	}

	public class Test {
		private Data data;

		public Data getData() {
			return data;
		}

		public void setData(Data data) {
			this.data = data;
		}
	}

	public class Data {
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

	public static void main(String[] args) {
		Test test1 = JsonHelper.fromJson("{\"data\":{\"id\":\"123455a\"}}", Test.class);
		Test test2 = JsonHelper.fromJson("{\"data\":{}}", Test.class);


		String json = JsonHelper.toJson(test1);
		String prettyJson = JsonHelper.toPrettyJson(json);
		if (test1 == null) {

		}
	}
}
