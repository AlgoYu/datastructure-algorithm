package cn.machine.geek.algorithm.other;

import java.io.*;
import java.util.*;

/**
 * @Author XiaoYu
 * @Description TODO 霍夫曼编码
 * @Datetime 2021/12/31 6:28 下午
 **/
public class HuffmanCodding {
    /**
     * 霍夫曼树节点
     */
    static class HuffmanNode {
        Byte data;
        int weight;
        HuffmanNode left;
        HuffmanNode right;
    }

    /**
     * 压缩文件
     *
     * @param inputPath  文件路径
     * @param outputPath 输出路径
     */
    public static void compressedFiles(String inputPath, String outputPath) {
        // 获取字节数据
        byte[] fileBytes = getFileBytes(inputPath);
        if (fileBytes == null || fileBytes.length == 0) {
            System.out.println("输入文件为空，不往下走。");
            return;
        }
        // 统计每个字节出现的次数
        Map<Byte, Integer> count = numberOfStatistics(fileBytes);
        // 构建Huffman树
        HuffmanNode huffmanTree = createHuffmanTree(count);
        // 创建Huffman编码
        Map<Byte, String> huffmanCoding = new HashMap<>();
        createHuffmanCodding(huffmanTree, new StringBuilder(), huffmanCoding);
        // 将二进制转换为Huffman编码
        byte[] huffmanBytes = convertBytes(fileBytes, huffmanCoding);
        // 创建输出文件
        createFile(outputPath);
        // 输出字节到文件
        outputBytesToFile(outputPath, huffmanBytes, huffmanCoding);
    }

    /**
     * 哈夫曼压缩，解压
     *
     * @param inputPath  文件路径
     * @param outputPath 输出路径
     */
    public static void deCompressedFiles(String inputPath, String outputPath) {
        // 解析出Huffman编码和字节数组
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;
        byte[] data = null;
        Map<Byte, String> huffmanCoding = null;
        try {
            inputStream = new FileInputStream(inputPath);
            objectInputStream = new ObjectInputStream(inputStream);

            data = (byte[]) objectInputStream.readObject();
            huffmanCoding = (Map<Byte, String>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (data == null) {
            System.out.println("解析错误");
            return;
        }

        // 还原数据
        byte[] sourceData = huffmanDecompression(data, huffmanCoding);
        File file = new File(outputPath);
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(sourceData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据Huffman编码还原数据
     *
     * @param data          Huffman编码字节数据
     * @param huffmanCoding Huffman编码
     * @return 原始数据
     */
    private static byte[] huffmanDecompression(byte[] data, Map<Byte, String> huffmanCoding) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            int temp = data[i];
            temp |= 256;
            if (i == data.length - 2) {
                sb.append(byteToString(temp, data[data.length - 1]));
            }
            sb.append(byteToString(temp, 8));
        }

        Map<String, Byte> deHuffmanCoding = new HashMap<>();
        for (Map.Entry<Byte, String> map : huffmanCoding.entrySet()) {
            deHuffmanCoding.put(map.getValue(), map.getKey());
        }

        List<Byte> byteList = new ArrayList<>();
        int n = sb.length();
        for (int i = 0; i < n; ) {
            int count = 1;
            while (true) {
                String key = sb.substring(i, i + count);
                Byte b = deHuffmanCoding.get(key);
                if (b != null) {
                    byteList.add(b);
                    break;
                }
                count++;
            }
            i += count;
        }
        byte[] sourceData = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            sourceData[i] = byteList.get(i);
        }
        return sourceData;
    }

    /**
     * 将Byte转换为二进制字符串
     *
     * @param b   比特
     * @param len 字符串长度
     * @return 字符串
     */
    public static String byteToString(int b, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (b >> 1 << 1 == b) {
                sb.append(0);
            } else {
                sb.append(1);
            }
            b >>= 1;
        }
        return sb.reverse().toString();
    }

    /**
     * 输出字节和编码到文件
     *
     * @param outputPath    输出字节到文件
     * @param data          字节数组
     * @param huffmanCoding 编码原始信息
     */
    private static void outputBytesToFile(String outputPath, byte[] data, Map<Byte, String> huffmanCoding) {
        File file = new File(outputPath);
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.writeObject(huffmanCoding);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将字节转换为Huffman编码
     *
     * @param huffmanCoding
     */
    private static byte[] convertBytes(byte[] fileBytes, Map<Byte, String> huffmanCoding) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileBytes.length; i++) {
            sb.append(huffmanCoding.get(fileBytes[i]));
        }
        int index = 0;
        int n = sb.length();
        // 保留两个字节，一个保留8位一个字节，除不尽的字符串，一个保留长度。
        byte[] huffmancoding = new byte[(n + 15) / 8];
        for (int i = 0; i < n; i += 8) {
            if (i + 8 > n) {
                huffmancoding[index] = (byte) Integer.parseInt(sb.substring(i), 2);
                huffmancoding[index + 1] = (byte) sb.substring(i).length();
            } else {
                huffmancoding[index] = (byte) Integer.parseInt(sb.substring(i, i + 8), 2);
            }
            index++;
        }
        return huffmancoding;
    }

    /**
     * 创建霍夫曼编码（深度优先）
     *
     * @param huffmanTree
     */
    private static void createHuffmanCodding(HuffmanNode huffmanTree, StringBuilder sb, Map<Byte, String> huffmanCoding) {
        if (huffmanTree == null) {
            return;
        }
        if (huffmanTree.data != null) {
            huffmanCoding.put(huffmanTree.data, sb.toString());
            return;
        }
        // 左
        sb.append(0);
        createHuffmanCodding(huffmanTree.left, sb, huffmanCoding);
        sb.delete(sb.length() - 1, sb.length());
        // 右
        sb.append(1);
        createHuffmanCodding(huffmanTree.right, sb, huffmanCoding);
        sb.delete(sb.length() - 1, sb.length());
    }

    /**
     * 构建一颗Huffman树
     *
     * @param count 统计结果
     * @return Huffman树
     */
    private static HuffmanNode createHuffmanTree(Map<Byte, Integer> count) {
        Queue<HuffmanNode> queue = new PriorityQueue<>((o1, o2) -> {
            return o1.weight - o2.weight;
        });
        for (Map.Entry<Byte, Integer> entry : count.entrySet()) {
            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.weight = entry.getValue();
            huffmanNode.data = entry.getKey();
            queue.add(huffmanNode);
        }
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode();
            parent.weight = left.weight + right.weight;
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }
        return queue.poll();
    }

    /**
     * 获取目标文件的字节数据
     *
     * @param inputPath 文件路径
     * @return 字节数据
     */
    private static byte[] getFileBytes(String inputPath) {
        File file = new File(inputPath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] data = new byte[fileInputStream.available()];
            int len = fileInputStream.read(data);
            System.out.println("读取文件" + len + "个字节");
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream == null) {
                return null;
            }
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 创建文件，包含路径
     *
     * @param outputPath 文件路径
     */
    private static void createFile(String outputPath) {
        File file = new File(outputPath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 统计每个字节出现的次数
     *
     * @param fileBytes 字节数据
     * @return 统计结果
     */
    private static Map<Byte, Integer> numberOfStatistics(byte[] fileBytes) {
        Map<Byte, Integer> count = new HashMap<>();
        for (byte b : fileBytes) {
            count.put(b, count.getOrDefault(b, 0) + 1);
        }
        return count;
    }
}
