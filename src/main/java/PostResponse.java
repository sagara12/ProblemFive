import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class PostResponse {
    public static void main(String[] args) throws IOException {
        int count = 0;
        List<String> totalList = new ArrayList<>();

        while (count < 100) {
            //HTTP 접속 구하기
            URL url = new URL("http://codingtest.brique.kr:8080/random");

            //리퀘스트 메소드를 Post로 설정하기
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //연결 타임 아웃 설정
            conn.setConnectTimeout(30000);

            //읽기 타임 아웃 설정
            conn.setReadTimeout(30000);

            /*//요청방식
            System.out.println("conn.getRequestMethod() = " + conn.getRequestMethod());

            //응답 콘텐츠 유형 구하기
            System.out.println("conn.getContentType() = " + conn.getContentType());

            //응답 코드 구하기
            System.out.println("conn.getResponseCode() = " + conn.getResponseCode());

            //응답 메세지
            System.out.println("conn.getResponseCode() = " + conn.getResponseCode());*/

            for (Map.Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
                for (String value : header.getValue()) {
                    System.out.println(header.getKey() + ":" + value);
                }
            }
            try (InputStream in = conn.getInputStream();
                 ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buf = new byte[1024 * 8];
                int length = 0;
                while ((length = in.read(buf)) != -1) {
                    out.write(buf, 0, length);
                }
                String value = new String(out.toByteArray(), "UTF-8");
                totalList.add(value);
            }

            conn.disconnect();
            count++;
        }


            /*System.out.println("totalList = " + totalList);
            System.out.println("totalList.size() = " + totalList.size());*/

            List<Log> CTList = new ArrayList<>();
            Set<String> set = new HashSet<String>(totalList);

            for (String str : set) {
                /*System.out.println(str + " : " + Collections.frequency(totalList, str));*/
                int count_val  = Collections.frequency(totalList, str);
                List<Log> countList = new ArrayList<>();
                Log log = new Log(str,count_val);
                CTList.add(log);
            }

            System.out.println(" ");
            System.out.println(" ");

            CTList.sort(Comparator.comparing(Log::getCount).reversed());
            for (int i = 0 ; i < CTList.size(); i++ ) {
                System.out.println("count :" + CTList.get(i).getCount() + CTList.get(i).getLog());
            }

            int total_int = 0;
             for (int i = 0 ; i < CTList.size(); i++ ) {
                int num_val =  CTList.get(i).getCount();
                 total_int = total_int + num_val;
            }
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Total count: " + total_int);

        System.out.println("  ");
        System.out.println("  ");
        System.out.println("종료하려면 exit를 입력 하세요");
        Scanner input = new Scanner(System.in);
        String esc = String.valueOf(input.hasNextLine());




        if (esc.equals("exit")) {
            System.exit(0);
        }
        }
}
