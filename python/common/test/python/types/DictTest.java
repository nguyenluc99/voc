package test.python.types;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.python.exceptions.AttributeError;
import org.python.exceptions.Exception;
import org.python.exceptions.KeyError;
import org.python.exceptions.TypeError;
import org.python.types.Dict;
import org.python.types.DictEnum;
import org.python.types.List;
import org.python.types.Object;
import org.python.types.Tuple;
import org.python.types.NoneType;
import org.python.types.Set;
import org.python.types.Str;
import org.python.types.Int;

import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test org.python.types.dict class
 * Last edited: 08/10/2020
 */
public class DictTest {

    Dict dict;
    Map<org.python.Object, org.python.Object> dict_map;
    Exception exception;

    /**
     * Test dict().__setattr__(String, Object) method
     */
    @Test
    @DisplayName("Test dict().__setattr__(String, Object)")
    public void test_setattr() {
        this.dict = new Dict();
        this.exception = assertThrows(AttributeError.class, () ->
            this.dict.__setattr__(DictEnum.ATTR.toString(), Int.getInt(42)));
        assertEquals(DictEnum.ATTR_ERR.toString(), this.exception.getMessage());
    }

    /**
     * Test dict().__getattr__(String)
     */
    @Test
    @DisplayName("Test dict().__getattr__(String)")
    public void test_getattr() {
        this.dict = new Dict();
        this.exception = assertThrows(AttributeError.class, () ->
            this.dict.__getattr__(DictEnum.ATTR.toString()));
        assertEquals(DictEnum.ATTR_ERR.toString(), this.exception.getMessage());
    }

    /**
     * Test new Dict() with dict().__repr__()
     */
    @Test
    @DisplayName("Test new Dict() with dict().__repr__()")
    public void test_creation() {
        this.dict = new Dict();
        assertEquals(this.dict.__repr__().toJava(), "{}");
        this.dict_map = new HashMap<>();
        this.dict_map.put(new Str("a"), Int.getInt(1));
        this.dict = new Dict(this.dict_map);
        assertEquals(this.dict.__repr__().toJava(), "{'a': 1}");
    }

    /**
     * Test dict().__contains__(Str) & dict().__getitem__(Str)
     */
    @Test
    @DisplayName("Test dict().__contains__(Str) & dict().__getitem__(Str)")
    public void test_getitem() {
        this.dict_map = new HashMap<>();
        this.dict_map.put(new Str("a"), Int.getInt(1));
        this.dict_map.put(new Str("b"), Int.getInt(2));
        this.dict_map.put(new Str("c"), Int.getInt(37));
        this.dict = new Dict(this.dict_map);
        assertTrue(this.dict.__contains__(new Str("a")).toBoolean());
        assertEquals(this.dict.__getitem__(new Str("a")), Int.getInt(1));
        this.dict_map.clear();
        this.dict_map.put(new Str("a"), Int.getInt(1));
        this.dict_map.put(new Str("b"), Int.getInt(2));
        this.dict = new Dict(this.dict_map);
        assertFalse(this.dict.__contains__(new Str("c")).toBoolean());
        this.exception = assertThrows(KeyError.class, () ->
            this.dict.__getitem__(new Str("c")));
        assertEquals("'c'", this.exception.getMessage());
    }

    /**
     * Test dict().clear(), dict().__repr__() & dict().__contains__()
     */
    @Test
    @DisplayName("Test dict().clear() with dict().__repr__() & dict().__contains__(Str)")
    public void test_clear() {
        this.dict_map = new HashMap<>();
        this.dict_map.put(new Str("a"), Int.getInt(1));
        this.dict_map.put(new Str("b"), Int.getInt(2));
        this.dict = new Dict(this.dict_map);
        assertTrue(this.dict.__contains__(new Str("a")).toBoolean());
        this.dict.clear();
        assertFalse(this.dict.__contains__(new Str("a")).toBoolean());
        assertEquals(this.dict.__repr__().toJava(), "{}");
        this.dict = new Dict();
        assertFalse(this.dict.__contains__(new Str("a")).toBoolean());
        this.dict.clear();
        assertFalse(this.dict.__contains__(new Str("a")).toBoolean());
        assertEquals(this.dict.__repr__().toJava(), "{}");
    }

    /**
     * Test new Dict() with dict(args).__repr__(), dict().__contains__(Str) & dict().get()
     */
    @Test
    @DisplayName("Test new Dict(args) with dict().__repr__(), dict().__contains__(Str) & dict().get()")
    public void test_builtin_constructor() {
        this.dict = new Dict();
        assertEquals(this.dict.__repr__().toJava(), "{}");
        assertFalse(this.dict.__contains__(new Str("a")).toBoolean());
        Object[] args = new Object[1];
        java.util.List<org.python.Object> org = new ArrayList<>(2);
        List testList = new List(org);
        java.util.List<org.python.Object> tempList = new ArrayList<>();
        tempList.add(new Str("a"));
        tempList.add(Int.getInt(1));
        Tuple tuple1 = new Tuple(tempList);
        java.util.List<org.python.Object> tempList2 = new ArrayList<>();
        tempList2.add(new Str("b"));
        tempList2.add(Int.getInt(2));
        Tuple tuple2 = new Tuple(tempList2);
        org.add(tuple1);
        org.add(tuple2);
        args[0] = testList;
        this.dict = new Dict(args, new HashMap<>());
        assertTrue(this.dict.__contains__(new Str("a")).toBoolean());
        assertEquals(this.dict.get(new Str("a"), null), Int.getInt(1));
        assertFalse(this.dict.__contains__(new Str("c")).toBoolean());
    }

    /**
     * Test new Dict(kwargs) with dict().__contains__(Str) & dict().__getitem__(Str)
     */
    @Test
    @DisplayName("Test new Dict() with dict().__contains__(Str) & dict().__getitem__(Str)")
    public void test_builtin_constructor_kwargs() {
        Map<java.lang.String, org.python.Object> dict_map = new HashMap<>();
        dict_map.put("a", Int.getInt(1));
        dict_map.put("b", Int.getInt(2));
        this.dict = new Dict(new Object[1], dict_map);
        assertTrue(this.dict.__contains__(new Str("a")).toBoolean());
        assertTrue(this.dict.__contains__(new Str("b")).toBoolean());
        assertFalse(this.dict.__contains__(new Str("c")).toBoolean());
        assertEquals(this.dict.__getitem__(new Str("b")), Int.getInt(2));
        dict_map.clear();
        dict_map.put("b", Int.getInt(3));
        Object[] args = new Object[1];
        args[0] = this.dict;
        this.dict = new Dict(args, dict_map);
        assertTrue(this.dict.__contains__(new Str("a")).toBoolean());
        assertTrue(this.dict.__contains__(new Str("b")).toBoolean());
        assertFalse(this.dict.__contains__(new Str("c")).toBoolean());
        assertEquals(this.dict.__getitem__(new Str("b")), Int.getInt(3));
    }

    /**
     * Test dict().pop(Object, Object)
     */
    @Test
    @DisplayName("Test dict().pop(Object, Object)")
    public void test_method_pop() {
        this.dict_map = new HashMap<>();
        this.dict_map.put(Int.getInt(1), Int.getInt(1));
        this.dict_map.put(Int.getInt(2), Int.getInt(2));
        this.dict = new Dict(this.dict_map);
        assertEquals(this.dict.pop(Int.getInt(1), null), Int.getInt(1));
        assertEquals(this.dict.pop(Int.getInt(2), Int.getInt(37)), Int.getInt(2));
        this.exception = assertThrows(KeyError.class, () ->
            this.dict.pop(Int.getInt(8), null));
        assertEquals("8", this.exception.getMessage());
        assertEquals(this.dict.pop(Int.getInt(7), Int.getInt(42)), Int.getInt(42));
    }

    /**
     * Test dict().setdefault(Object, Object) with __getitem__(Object)
     */
    @Test
    @DisplayName("Test dict().setdefault(Object, Object) with __getitem__(Object)")
    public void test_method_setdefault() {
        this.dict_map = new HashMap<>();
        this.dict_map.put(Int.getInt(42), new Str("Babel"));
        this.dict = new Dict(this.dict_map);
        assertEquals(this.dict.setdefault(Int.getInt(42), null), new Str("Babel"));
        assertNotEquals(this.dict.setdefault(new Str("David"), new Str("Gilmour")), new Str("Babel"));
        assertEquals(this.dict.setdefault(Int.getInt(1), null), NoneType.NONE);
        assertEquals(this.dict.__getitem__(Int.getInt(1)), NoneType.NONE);
        this.exception = assertThrows(TypeError.class, () ->
            this.dict.setdefault(new List(), Int.getInt(42)));
        assertEquals(DictEnum.TYPE_ERR_LIST.toString(), this.exception.getMessage());
    }

    /**
     * Test dict().copy() with dict().__repr__() & dict().__eq_(dict)
     */
    @Test
    @DisplayName("Test dict().copy() with dict().__repr__() & dict().__eq_(dict)")
    public void test_copy() {
        this.dict_map = new HashMap<>();
        this.dict_map.put(Int.getInt(42), new Str("Babel"));
        this.dict = new Dict(this.dict_map);
        Dict dict_y = (Dict) this.dict.copy();
        assertEquals(dict_y.__repr__().toJava(), "{42: 'Babel'}");
        assertTrue(this.dict.__eq__(dict_y).toBoolean());
        assertNotSame(this.dict, dict_y);
    }

    /**
     * Test dict().update(Object, Dict) with dict().__repr__()
     */
    @Test
    @DisplayName("Test dict().update(Object, Dict) with dict().__repr__()")
    public void test_update() {
        this.dict_map = new HashMap<>();
        Map<org.python.Object, org.python.Object> kwargsMap = new HashMap<>();
        kwargsMap.put(new Str("a"), Int.getInt(1));
        kwargsMap.put(new Str("b"), Int.getInt(2));
        this.dict = new Dict(kwargsMap);
        Dict kwargsDict = new Dict(this.dict_map);
        kwargsDict.update(null, this.dict);
        assertEquals(kwargsDict.__repr__().toJava(), "{'a': 1, 'b': 2}");

        kwargsMap.clear();
        this.dict = new Dict(kwargsMap);
        kwargsMap.put(new Str("a"), Int.getInt(1));
        kwargsMap.put(new Str("b"), Int.getInt(2));
        Dict dictDict = new Dict(this.dict_map);
        dictDict.update(this.dict, null);
        assertEquals(dictDict.__repr__().toJava(), "{'a': 1, 'b': 2}");

        java.util.List<org.python.Object> org = new ArrayList<>(2);
        List testList = new org.python.types.List(org);

        java.util.List<org.python.Object> tempList = new ArrayList<>(2);
        tempList.add(new Str("a"));
        tempList.add(Int.getInt(1));
        Tuple tuple1 = new Tuple(tempList);

        java.util.List<org.python.Object> tempList2 = new ArrayList<>(2);
        tempList2.add(new Str("a"));
        tempList2.add(Int.getInt(1));
        Tuple tuple2 = new Tuple(tempList2);

        org.add(tuple1);
        org.add(tuple2);

        Dict tupleDict = new Dict(this.dict_map);
        tupleDict.update(testList, null);
        assertEquals(tupleDict.__repr__().toJava(), "{'a': 1, 'b': 2}");
    }

//    @Test(expected = NullPointerException.class)
    @Test
    public void test_fromkeys_missing_iterable() {
	try {
	    System.out.println(Dict.fromkeys(null, null));
	} catch (TypeError err) {
	    assertEquals("fromkeys expected at least 1 arguments, got 0", err.toString());
	}
    }

    @Test
    public void test_values() {
	Map<org.python.Object, org.python.Object> dict_map = new HashMap<>();
	dict_map.put(Int.getInt(1), Int.getInt(1));
	dict_map.put(Int.getInt(2), Int.getInt(2));
	dict_map.put(Int.getInt(3), Int.getInt(3));

	Dict dict = new Dict(dict_map);

	org.python.Object y = dict.values();

	assertEquals(y.type().__repr__().toJava(), "<class 'dict_values'>");
	assertEquals(y.__len__(), Int.getInt(3));
	assertTrue(dict.__contains__(Int.getInt(3)).toBoolean());
	assertTrue(!dict.__contains__(Int.getInt(42)).toBoolean());

	Int z = (Int) y.__len__();

//	assertTrue("Working as expected", z.value > 0);

	int s = 0;

	for (org.python.Object value : dict_map.values()) {
	    Int v = (Int) value;
	    s += v.value;
	}

	assertEquals(s, 6);
    }

    public Tuple createPythonIntegerTuple(int x, int y) {
	java.util.List<org.python.Object> tmpTupleList = new ArrayList<>();
	tmpTupleList.add(Int.getInt(x));
	tmpTupleList.add(Int.getInt(y));

	return new Tuple(tmpTupleList);
    }

    public Set javaSetToPythonSet(java.util.Set<java.util.Map.Entry<org.python.Object, org.python.Object>> set) {
	Set resultSet = new Set();

	for (java.util.Map.Entry<org.python.Object, org.python.Object> tuple : set) {
	    Int a = (Int) tuple.getKey();
	    Int b = (Int) tuple.getValue();
	    resultSet.add(createPythonIntegerTuple((int) a.value, (int) b.value));
	}
	return resultSet;
    }

    @Test
    public void test_items() {
	Map<org.python.Object, org.python.Object> dict_map_x = new HashMap<>();
	dict_map_x.put(Int.getInt(1), Int.getInt(1));
	dict_map_x.put(Int.getInt(2), Int.getInt(2));
	dict_map_x.put(Int.getInt(3), Int.getInt(3));

	Map<org.python.Object, org.python.Object> dict_map_x2 = new HashMap<>();
	dict_map_x2.put(Int.getInt(1), Int.getInt(1));
	dict_map_x2.put(Int.getInt(2), Int.getInt(2));
	dict_map_x2.put(Int.getInt(3), Int.getInt(3));
	dict_map_x2.put(Int.getInt(4), Int.getInt(4));

	Dict dict_x = new Dict(dict_map_x);
	Dict dict_x2 = new Dict(dict_map_x2);

	org.python.Object y = dict_x.items();
	org.python.Object y2 = dict_x2.items();

	Int len_of_y = (Int) y.__len__();

	assertEquals(len_of_y, Int.getInt(3));

	Tuple tmpTuple = createPythonIntegerTuple(1, 1);

	assertTrue(y.__contains__(tmpTuple).toBoolean());

	Tuple tmpTuple2 = createPythonIntegerTuple(1, 2);

	assertFalse(y.__contains__(tmpTuple2).toBoolean());

	int s1 = 0;
	int s2 = 0;

	for (org.python.Object key : dict_map_x.keySet()) {
	    Int k = (Int) key;
	    s1 += k.value;
	}

	for (org.python.Object value : dict_map_x.values()) {
	    Int v = (Int) value;
	    s2 += v.value;
	}

	assertEquals(s1, 6);
	assertEquals(s2, 6);

	java.util.Set<java.util.Map.Entry<org.python.Object, org.python.Object>> sList = new HashSet<Map.Entry<org.python.Object, org.python.Object>>();
	sList.add(new java.util.AbstractMap.SimpleEntry<>(Int.getInt(1), Int.getInt(1)));
	sList.add(new java.util.AbstractMap.SimpleEntry<>(Int.getInt(1), Int.getInt(2)));
	sList.add(new java.util.AbstractMap.SimpleEntry<>(Int.getInt(2), Int.getInt(2)));
	sList.add(new java.util.AbstractMap.SimpleEntry<>(Int.getInt(2), Int.getInt(3)));

	Set sListPython = javaSetToPythonSet(sList);
	Set yListPython = javaSetToPythonSet(dict_map_x.entrySet());

	assertEquals(yListPython.intersection(sListPython).__repr__().toJava(), "{(1, 1), (2, 2)}");
	assertEquals(yListPython.intersection(sListPython).__len__(), Int.getInt(2));

	assertEquals(yListPython.union(sListPython).__len__(), Int.getInt(5));

	assertEquals(yListPython.__xor__(sListPython).__len__(), Int.getInt(3));

	assertEquals(yListPython.difference(sListPython).__len__(), Int.getInt(1));
    }

    @Test
    public void testPythonConstructorArgs() {
	org.python.Object[] args = { null };
	Dict dict = new Dict(args, new HashMap<>());
	assertEquals(dict.__repr__().toJava(), "{}");

	org.python.Object[] args2 = { dict };
	dict = new Dict(args2, new HashMap<>());
	assertEquals(dict.__repr__().toJava(), "{}");
    }

    @Test
    public void testPythonConstructorKwargs() {
	org.python.Object[] args = { null };
	Map<String, org.python.Object> kwargs = new HashMap<>();

	Dict dict = new Dict(args, kwargs);
	assertEquals(dict.__repr__().toJava(), "{}");

	kwargs.put("a", org.python.types.Int.getInt(42));
	dict = new Dict(args, kwargs);
	assertEquals(dict.__repr__().toJava(), "{'a': 42}");

	kwargs = new HashMap<>();
	kwargs.put("a", org.python.types.Int.getInt(42));
	kwargs.put("b", org.python.types.Int.getInt(2));
	kwargs.put("c", org.python.types.Int.getInt(1));
	kwargs.put("d", org.python.types.Int.getInt(8));
	dict = new Dict(args, kwargs);
	assertEquals(dict.__repr__().toJava(), "{'a': 42, 'b': 2, 'c': 1, 'd': 8}");
    }

    @Test
    public void testPythonConstructorTuples() {
	Map<String, org.python.Object> kwargs = new HashMap<>();
	java.util.List<org.python.Object> dictionaryElements = new ArrayList<>();

	org.python.types.List arg = new org.python.types.List();
	org.python.Object[] args = { arg };
	Dict dict = new Dict(args, kwargs);
	assertEquals(dict.__repr__().toJava(), "{}");

	arg = new org.python.types.List();
	dictionaryElements = new ArrayList<>();
	dictionaryElements.add(new org.python.types.Str("a"));
	dictionaryElements.add(org.python.types.Int.getInt(42));

	arg.append(new org.python.types.Tuple(dictionaryElements));
	org.python.Object[] args2 = { arg };
	dict = new Dict(args2, kwargs);
	assertEquals(dict.__repr__().toJava(), "{'a': 42}");
    }

    @Test
    public void testPythonConstructorLists() {
	Map<String, org.python.Object> kwargs = new HashMap<>();

	org.python.types.List dictionaryElement = new org.python.types.List();
	dictionaryElement.append(new org.python.types.Str("a"));
	dictionaryElement.append(org.python.types.Int.getInt(42));

	org.python.types.List arg = new org.python.types.List();
	arg.append(dictionaryElement);

	org.python.Object[] args = { arg };
	Dict dict = new Dict(args, kwargs);
	assertEquals(dict.__repr__().toJava(), "{'a': 42}");
    }

    @Test
    public void testPythonConstructorStrings() {
	Map<String, org.python.Object> kwargs = new HashMap<>();

	org.python.types.Str dictionaryElement = new org.python.types.Str("ab");

	org.python.types.List arg = new org.python.types.List();
	arg.append(dictionaryElement);

	org.python.Object[] args = { arg };
	Dict dict = new Dict(args, kwargs);
	assertEquals(dict.__repr__().toJava(), "{'a': 'b'}");
    }

//    @Test(expected = org.python.exceptions.TypeError.class)
    @Test
    public void testPythonConstructorIcorrectArg() {
	Map<String, org.python.Object> kwargs = new HashMap<>();

	org.python.types.List arg = new org.python.types.List();
	arg.append(org.python.types.Bool.getBool(true));

	org.python.Object[] args2 = { arg };
	Dict dict = new Dict(args2, kwargs);
    }

//    @Test(expected = org.python.exceptions.ValueError.class)
    @Test
    public void testPythonConstructorIncorrectLengthOfIterable() {
	Map<String, org.python.Object> kwargs = new HashMap<>();
	java.util.List<org.python.Object> dictionaryElements = new ArrayList<>();

	org.python.types.List arg = new org.python.types.List();
	dictionaryElements.add(new org.python.types.Str("a"));
	dictionaryElements.add(org.python.types.Int.getInt(42));
	dictionaryElements.add(org.python.types.Int.getInt(100));

	arg.append(new org.python.types.Tuple(dictionaryElements));
	org.python.Object[] args2 = { arg };
	Dict dict = new Dict(args2, kwargs);
    }
}
