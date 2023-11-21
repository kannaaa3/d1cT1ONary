package unittest;

import model.util.Algorithm;
import model.util.Converter;
import org.junit.Assert;
import org.junit.Test;

public class ModelUtilUnitTest {
    @Test
    public void convertBinaryArrayToStringTest1() {
        Assert.assertEquals(Converter.convertBinaryArrayToString(new Integer[]{
                0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1,
                0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0,
                0, 1, 1, 0, 1, 1, 1, 1
        }), "hello");
    }

    @Test
    public void convertBinaryArrayToStringTest2() {
        Assert.assertEquals(Converter.convertBinaryArrayToString(new Integer[]{
                0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0,
                0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1,
                0, 1, 1, 1, 0, 0, 1, 0
        }), "UPPer");
    }

    @Test
    public void convertBinaryArrayToStringTest3() {
        Assert.assertEquals(Converter.convertBinaryArrayToString(new Integer[]{
                0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0 ,0, 0, 0, 0, 0,
                0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0,
                0, 1, 1, 1, 0, 1, 1, 0
        }), "A@50G^v");
    }

    @Test
    public void convertBinaryArrayToStringTest4() {
        Assert.assertEquals(Converter.convertBinaryArrayToString(new Integer[]{
                0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0,
                0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1,
                0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0
        }), "|zk_V>");
    }

    @Test
    public void convertBinaryArrayToStringTest5() {
        Assert.assertEquals(Converter.convertBinaryArrayToString(new Integer[]{
                0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1,
                0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0,
                0, 0, 1, 1, 1, 0, 0, 1
        }), "!#&69");
    }

    @Test
    public void convertStringToArrayTest1() {
        Assert.assertArrayEquals(Converter.convertStringToBinaryArray("hello"),
                new Integer[]{
                        0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1,
                        0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0,
                        0, 1, 1, 0, 1, 1, 1, 1
                });
    }

    @Test
    public void convertStringToArrayTest2() {
        Assert.assertArrayEquals(Converter.convertStringToBinaryArray("UPPer"),
                new Integer[]{
                        0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0,
                        0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1,
                        0, 1, 1, 1, 0, 0, 1, 0
                });
    }

    @Test
    public void convertStringToArrayTest3() {
        Assert.assertArrayEquals(Converter.convertStringToBinaryArray("A@50G^v"),
                new Integer[]{
                        0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0 ,0, 0, 0, 0, 0,
                        0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0,
                        0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0,
                        0, 1, 1, 1, 0, 1, 1, 0
                });
    }

    @Test
    public void convertStringToArrayTest4() {
        Assert.assertArrayEquals(Converter.convertStringToBinaryArray("|zk_V>"),
                new Integer[]{
                        0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0,
                        0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1,
                        0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0
                });
    }

    @Test
    public void convertStringToArrayTest5() {
        Assert.assertArrayEquals(Converter.convertStringToBinaryArray("!#&69"),
                new Integer[]{
                        0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1,
                        0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0,
                        0, 0, 1, 1, 1, 0, 0, 1
                });
    }

    @Test
    public void sortTest1() {
        String[] arr = new String[]{
                "crab", "fish", "egg", "crave", "fir"
        };
        Algorithm.sort(arr);
        Assert.assertArrayEquals(arr, new String[]{
                "crab", "crave", "egg", "fir", "fish"
        });
    }

    @Test
    public void sortTest2() {
        Integer[] arr = new Integer[]{
                7, 4, 1, 9, 2
        };
        Algorithm.sort(arr);
        Assert.assertArrayEquals(arr, new Integer[]{
                1, 2, 4, 7, 9
        });
    }

    @Test
    public void sortTest3() {
        Integer[] arr = new Integer[]{
                3, 1, 4, 1, 5, 9, 2, 6, 5, 4
        };
        Algorithm.sort(arr);
        Assert.assertArrayEquals(arr, new Integer[]{
                1, 1, 2, 3, 4, 4, 5, 5, 6, 9
        });
    }

    @Test
    public void sortTest4() {
        Integer[] arr = new Integer[]{
                10, 9, 8, 7, 6, 5, 4, 3, 2, 1
        };
        Algorithm.sort(arr);
        Assert.assertArrayEquals(arr, new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        });
    }

    @Test
    public void sortTest5() {
        Integer[] arr = new Integer[]{
                10, 1, 9, 8, 2, 7, 3, 6, 4, 5
        };
        Algorithm.sort(arr);
        Assert.assertArrayEquals(arr, new Integer[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10
        });
    }

    @Test
    public void containsTest1() {
        Assert.assertFalse(Algorithm.contains(new Integer[]{
                1, 3, 5, 7, 9
        }, 4));
    }

    @Test
    public void containsTest2() {
        Assert.assertTrue(Algorithm.contains(new Integer[]{
                1, 3, 5, 7, 9
        }, 5));
    }

    @Test
    public void containsTest3() {
        Assert.assertTrue(Algorithm.contains(new Integer[]{
                1, 3, 5, 7, 9, 11
        }, 1));
    }

    @Test
    public void containsTest4() {
        Assert.assertTrue(Algorithm.contains(new Integer[]{
                1, 3, 5, 7, 9, 11
        }, 11));
    }

    @Test
    public void containsTest5() {
        Assert.assertFalse(Algorithm.contains(new Integer[]{
                1, 3, 5, 7, 9, 11
        }, -3));
    }
}
