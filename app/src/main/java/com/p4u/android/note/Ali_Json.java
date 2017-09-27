package com.p4u.android.note;

/**
 * Created by ShaoZhen-PC on 2017/9/21.
 */

public class Ali_Json {
    /**
     // 字符串转换成json数据
     String str = "{'name':'zhang','age':20}";
     String str_json1 = JSON.toJSONString(str, true);
     String str_json2 = JSON.toJSONString(str, false);
     System.out.println("格式化数据" + str_json1);
     System.out.println("未格式化数据" + str_json2);
     // json转化简单的实体类
     String stu = "{'name':'lisi','age':22}";
     Student ss = JSON.parseObject(stu, Student.class);
     System.out.println(ss);
     // 输出jsonObject中的数据
     JSONObject object = JSON.parseObject(stu);
     System.out.println("获取json数据中的数据       " + object.get("name") + " " + object.get("age"));
     // 删除json中的数据
     String propertyName = "name";
     Set set = object.keySet();
     set.remove(propertyName);
     // object.remove(propertyName);
     System.out.println("删除数据之后的json格式  " + object.toString());
     // json转化list集合
     // String list = "[{'name':'zhang','age':20},{'name':'li','age':30}]";
     // 添加属性value值
     String addPropertyName = "sex";
     String addPropertyVlaue = "man";
     object.put(addPropertyName, addPropertyVlaue);
     System.out.println("输出新增后的json数据   " + object.toString());
     // 修改属性的值等同于覆盖值
     String updatePropertyName = "sex";
     String updatePropertyVlaue = "woman";
     Set set2 = object.keySet();
     if (set2.contains(updatePropertyName)) {
     // object.put(updatePropertyName, JSON.toJSONString(updatePropertyVlaue));
     object.put(updatePropertyName, updatePropertyVlaue);
     }
     System.out.println("输出修改属性值的json数据   " + object.toString());
     // 判断json是否存在属性
     System.out.println("是否存在属性值id  " + object.keySet().contains("id"));
     // 转换日期，这个还是比较重要
     Object date = new Date();
     String date_json = JSON.toJSONStringWithDateFormat(date, "yyyy-MM-dd HH:mm:ss.SSS");
     System.out.println("日期处理  " + date_json);
     // 解析Map集合对象
     Map<String, String> map = new HashMap<String, String>();
     map.put("name", "李四");
     map.put("age", "20");
     String map_json = JSON.toJSONString(map);
     System.out.println("map转换成json数据     " + map_json);

     Map<String, String> map1 = new HashMap<String, String>();
     map1.put("code", "11");
     map1.put("message", "ok");
     String json = JSON.toJSONString(map1);
     JSONObject jsonObject = JSON.parseObject(json);
     System.out.println("获取map集合中的数据   " + jsonObject.get("code") + "  " + jsonObject.get("message"));

     // 解析多个对象成list集合 使用JSONArray数组
     String array = "[{'name':'zhang','age':20},{'name':'wang','age':21}]";
     List<Student> stu_list = new ArrayList<Student>(JSONArray.parseArray(array, Student.class));
     System.out.println("输出集合大小  " + stu_list.size());
     for (Student s : stu_list) {
     System.out.println("遍历每一个对象    " + s);
     }　　
     */
}
