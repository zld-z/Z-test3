import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Province {
    public static List<String> sf = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        List<String> sf1 = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        String path = "C:\\Users\\DELL\\Desktop\\%s";
        String in = s.nextLine();
        String[] ss = in.split(" ");
        String name = "";
        if (ss.length == 3){
            name = ss[2];
        }
        List<String> outList = new ArrayList<>();
        List<String> list = toArrayByFileReader(String.format(path,ss[0]));
        if (null != name && !"".equals(name) ){
            //查询
            String finalName = name;
            List<String> finalOutList = outList;
            list.forEach(e -> {
                if (e.contains(finalName)){
                    finalOutList.add(e);
                }
            });
            String finalName1 = name;
            sf1 = sf.stream().filter(e -> e.equals(finalName1)).collect(Collectors.toList());
        }else{
            sf1 = sf;
            outList = list;
        }
        LinkedHashSet h = new LinkedHashSet(sf1);
        sf1.clear();
        sf1.addAll(h);
        writeFile(sf1, outList, String.format(path,ss[1]));
    }

    public static void writeFile(List<String> sf2, List<String> outList, String name) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(name));
        if (null != outList && outList.size() > 0){
            sf2.forEach(e -> {
                pw.write(e);
                pw.write("\n");
                outList.forEach(e2 ->{
                    if (e2.contains(e)) {
                        pw.write(e2.replace(e, ""));
                        pw.write("\n");
                    }
                });
            });

        }
        pw.close();
    }

    public static List<String> toArrayByFileReader(String name) {
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(name);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
                sf.add(str.substring(0,3));
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 返回数组
        return arrayList;
    }

}
