package common.basic.utils;

//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import org.junit.Assert;

public class GsonUtilTest extends Assert {

//    String json;
//    JsonElement jsonElement;
//
//    @Before
//    public void setUp(){
//        json = GsonUtil.toJson(new Member("kang", 27));
//
//        String stringJson = "{\"name\":\"kang\",\"age\":27}";
//        jsonElement = GsonUtil.fromString(stringJson);
//    }
//
//    @Test(expected = InstantiationException.class)
//    public void testConstructor() throws Exception {
//        new GsonUtil();
//    }
//
//    @Test
//    public void testToJson(){
//        String result = "{\"name\":\"kang\",\"age\":27}";
//
//        assertEquals(result, json);
//    }
//
//    @Test
//    public void testFromJson() throws Exception{
//        final Member member = GsonUtil.fromJson(json, Member.class);
//
//        assertEquals("kang", member.getName());
//        assertEquals(27, member.getAge());
//
//        final JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("name", "kang");
//        jsonObject.addProperty("age",27);
//
//        final Member memberJsonObject = GsonUtil.fromJson(jsonObject, Member.class);
//        assertEquals("kang", memberJsonObject.getName());
//        assertEquals(27, memberJsonObject.getAge());
//    }
//
//    @Test
//    public void testFromString(){
//        final JsonObject jsonObject = jsonElement.getAsJsonObject();
//        assertEquals("kang", jsonObject.get("name").getAsString());
//        assertEquals(27, jsonObject.get("age").getAsInt());
//    }
//
//    @Test
//    public void testGet(){
//        assertEquals("kang", GsonUtil.get(jsonElement, "name").getAsString());
//
//        assertEquals("kang", GsonUtil.get(jsonElement, "name", "default"));
//        assertEquals("default", GsonUtil.get(jsonElement, "string", "default"));
//
//        assertEquals(27, GsonUtil.get(jsonElement, "age", 19.19), 0.1);
//        assertEquals(19.19, GsonUtil.get(jsonElement, "double", 19.19), 0.1);
//
//        assertEquals(27, GsonUtil.get(jsonElement, "age", 119));
//        assertEquals(119, GsonUtil.get(jsonElement, "long", 119));
//    }

}

class Member{
    String name;
    int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

}
