import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static Folder main;
    static Map<String, Folder> folderMap;
    static Map<String, Integer> fileMap;
    static Map<String, String> mvFolder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        main = new Folder(null);
        folderMap = new HashMap<>();
        fileMap = new HashMap<>();
        folderMap.put("main", main);
        mvFolder = new HashMap<>();

        Queue<String> q = new ArrayDeque<>();
        while (N + M-- > 0) {
            q.offer(br.readLine());
        }

        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            String[] A = st.nextToken().split("/");
            String[] B = st.nextToken().split("/");
            String aName = A[A.length - 1];
            String bName = B[B.length - 1];
            folderMap.put(aName, new Folder(null));
            folderMap.put(bName, new Folder(null));
            mvFolder.put(A[A.length - 1], B[B.length - 1]);
        }

        while (!q.isEmpty()) {
            st = new StringTokenizer(q.poll());
            String folderName = st.nextToken();
            while(mvFolder.containsKey(folderName)) {
                folderName = mvFolder.get(folderName);
            }
            Folder folder = folderMap.getOrDefault(folderName, new Folder(null));
            if (!folderMap.containsKey(folderName)) {
                folderMap.put(folderName, folder);
            }
            String name = st.nextToken();
            if (st.nextToken().charAt(0) == '1') {
                if (folderMap.containsKey(name)) {
                    Folder currFolder = folderMap.get(name);
                    currFolder.parent = folder;
                    while (folder != null) {
                        folder.files.addAll(currFolder.files);
                        folder.fileCount += currFolder.fileCount;
                        folder = folder.parent;
                    }
                } else {
                    Folder newFolder = new Folder(folder);
                    folderMap.put(name, newFolder);
                }
            } else {
                int fileIndex = fileMap.containsKey(name) ? fileMap.get(name) : fileMap.size();
                if (fileIndex == fileMap.size())
                    fileMap.put(name, fileIndex);
                if(folder.files.contains(fileIndex))
                    continue;
                while (folder != null) {
                    folder.files.add(fileIndex);
                    folder.fileCount++;
                    folder = folder.parent;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            String[] input = br.readLine().split("/");
            Folder folder = folderMap.get(input[input.length - 1]);
            sb.append(folder.files.size()).append(" ").append(folder.fileCount).append("\n");
        }
        System.out.println(sb);
    }

    private static class Folder {
        Folder parent;
        Set<Integer> files;
        int fileCount;

        public Folder(Folder parent) {
            this.parent = parent;
            this.files = new HashSet<>();
            this.fileCount = 0;
        }
    }
}