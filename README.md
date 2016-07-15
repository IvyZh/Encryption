# Encryption
Java 常见加密算法

* 说明：本来Java加密是可以用一个简单的JavaSe程序来完成的，这里我就用Android程序来演示了


> 更详尽的加密描述请参考这篇[博客](http://blog.csdn.net/amurocrash/article/details/51916516)


* Base64
	* Base64是一种任意二进制到文本字符串的编码方法，常用于在URL、Cookie、网页中传输少量二进制数据。
	* 所谓Base64，就是说选出64个字符----小写字母a-z、大写字母A-Z、数字0-9、符号"+"、"/"（再加上作为垫字的"="，实际上是65个字符）----作为一个基本字符集。然后，其他所有符号都转换成这个字符集中的字符。具体来说，转换方式可以分为四步。

步骤：

	第一步，将每三个字节作为一组，一共是24个二进制位。
	第二步，将这24个二进制位分为四组，每个组有6个二进制位。
	第三步，在每组前面加两个00，扩展成32个二进制位，即四个字节。
	第四步，根据下表，得到扩展后的每个字节的对应符号，这就是Base64的编码值。


详细的原理，可以参考[阮一峰](http://www.ruanyifeng.com/blog/2008/06/base64.html)的博客，比较好理解。


Base64加解密Code：

    private void base64(String content) {

        if(switch1.isChecked()){//加密
            byte[] bytes = Base64.encode(content.getBytes(),0);
            String result = new String(bytes);
            setText(result);
        }else{//解密
            byte[] decode = Base64.decode(content,0);
            String result = new String(decode);
            setText(result);
        }
    }


* MD5

消息摘要（Message Digest）又称为数字摘要(Digital Digest)。它是一个唯一对应一个消息或文本的固定长度的值，它由一个单向Hash加密函数对消息进行作用而产生。HASH函数的抗冲突性使得如果一段明文稍有变化，哪怕只更改该段落的一个字母，通过哈希算法作用后都将产生不同的值。而HASH算法的单向性使得要找到哈希值相同的两个不同的输入消息，在计算上是不可能的。所以数据的哈希值，即消息摘要，可以检验数据的完整性。 

    private void md5(String content) {
        if(switch1.isChecked()){//加密
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] bytes = digest.digest(content.getBytes());
            String result = byteArrayToHexString(bytes);
            setText(result);
        }else{//解密
            setText("此方式无解密算法");
        }
    }

    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }


* SHA

code:

    private void sha(String content) {
        if(switch1.isChecked()){//加密
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] bytes = digest.digest(content.getBytes());
            String result = byteArrayToHexString(bytes);
            setText(result);
        }else{//解密
            setText("此方式无解密算法");
        }
    }