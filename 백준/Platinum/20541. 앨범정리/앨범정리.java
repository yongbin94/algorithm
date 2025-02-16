import java.io.*;
import java.util.*;

public class Main {
    static Album root, current;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        root = new Album("album", null);
        current = root;
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "mkalb":
                    current.makeAlbum(st.nextToken());
                    break;
                case "rmalb":
                    current.removeAlbum(st.nextToken());
                    break;
                case "insert":
                    current.insertPhoto(st.nextToken());
                    break;
                case "delete":
                    current.deletePhoto(st.nextToken());
                    break;
                case "ca":
                    current.changeAlbum(st.nextToken());
                    break;

            }
        }
        System.out.println(sb);
    }

    private static class Album {
        String name;
        Album parent;
        TreeSet<String> children;
        Map<String, Album> albumMap;
        TreeSet<String> photos;

        public Album(String name, Album parent) {
            this.name = name;
            this.parent = parent;
            children = new TreeSet<>();
            albumMap = new HashMap<>();
            photos = new TreeSet<>();
        }

        public void makeAlbum(String s) {
            if (children.contains(s)) {
                sb.append("duplicated album name\n");
                return;
            }
            children.add(s);
            Album newAlbum = new Album(s, this);
            this.albumMap.put(s, newAlbum);
        }

        public void removeAlbum(String s) {
            int albumCnt = 0;
            int photoCnt = 0;
            Queue<Album> q = new ArrayDeque<>();
            if (isName(s)) {
                if (children.contains(s)) {
                    q.offer(albumMap.get(s));
                    children.remove(s);
                    albumMap.remove(s);
                }
            } else if (!children.isEmpty()) {
                int cmd = Integer.parseInt(s);
                if (cmd == 0) {
                    while (!children.isEmpty()) {
                        String name = children.pollLast();
                        q.offer(albumMap.get(name));
                    }
                    albumMap.clear();
                } else {
                    String name = cmd == -1 ? children.pollFirst() : children.pollLast();
                    q.offer(albumMap.get(name));
                    albumMap.remove(name);
                }
            }
            while (!q.isEmpty()) {
                Album album = q.poll();
                while (!album.children.isEmpty()) {
                    String name = album.children.pollLast();
                    q.offer(album.albumMap.get(name));
                }
                album.albumMap.clear();
                albumCnt++;
                photoCnt += album.photos.size();
            }
            sb.append(albumCnt).append(" ").append(photoCnt).append("\n");
        }

        public void insertPhoto(String s) {
            if (photos.contains(s)) {
                sb.append("duplicated photo name\n");
                return;
            }
            photos.add(s);
        }

        public void deletePhoto(String s) {
            int photoCnt = 0;
            if (isName(s)) {
                if (photos.contains(s)) {
                    photos.remove(s);
                    photoCnt = 1;
                }
            } else if (!photos.isEmpty()) {
                int cmd = Integer.parseInt(s);
                if (cmd == 0) {
                    photoCnt = photos.size();
                    photos.clear();
                } else {
                    if (cmd == -1) photos.pollFirst();
                    else photos.pollLast();
                    photoCnt = 1;
                }
            }
            sb.append(photoCnt).append("\n");
        }

        public void changeAlbum(String s) {
            if (s.charAt(0) == '.') {
                if (parent != null) {
                    current = parent;
                }
            } else if (s.charAt(0) == '/') {
                current = root;
            } else {
                if (children.contains(s)) {
                    current = albumMap.get(s);
                }
            }
            sb.append(current.name).append("\n");
        }

        private boolean isName(String s) {
            try {
                Integer.parseInt(s);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        }
    }
}