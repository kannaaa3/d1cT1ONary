package unittest;

import model.datastructure.RadixTree;
import org.junit.Assert;
import org.junit.Test;

public class ModelDataStructureUnitTest {
    @Test
    public void TestRadixTreeContain1() {
        RadixTree radixTree = new RadixTree();
        for (int i = 1; i <= 20; i++) {
            Integer[] tmp = new Integer[i];
            for (int j = 0; j < i; j++) {
                tmp[j] = 1;
            }
            radixTree.add(tmp);
        }
        Assert.assertFalse(radixTree.contains(new Integer[]{
                1, 0, 1, 0, 1
        }));
    }

    @Test
    public void TestRadixTreeContain2() {
        RadixTree radixTree = new RadixTree();
        Integer[][] test = new Integer[][]{
                {1, 0, 1, 0, 1},
                {1, 1, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 1, 1},
                {0, 1, 0},
                {0, 0, 1, 0, 1},
                {1, 1, 0, 1, 0, 1, 0}
        };
        for (Integer[] integers : test) {
            radixTree.add(integers);
        }
        for (Integer[] integers : test) {
            Assert.assertTrue(radixTree.contains(integers));
        }
        Integer[][] notInTest = new Integer[][]{
                {1, 0},
                {1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0}
        };
        for (Integer[] integers : notInTest) {
            Assert.assertFalse(radixTree.contains(integers));
        }
    }
}