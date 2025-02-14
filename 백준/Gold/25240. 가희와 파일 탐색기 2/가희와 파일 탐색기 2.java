import java.io.*;
import java.util.*;

public class Main {
    static int U, F, Q;
    static Map<String, Integer> users;
    static Map<String, Integer> groups;
    static List<Group> groupList;
    static Map<String, File> files;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        U = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        users = new HashMap<>();
        groups = new HashMap<>();
        groupList = new ArrayList<>();
        files = new HashMap<>();
        while (U-- > 0) {
            st = new StringTokenizer(br.readLine());
            String userName = st.nextToken();
            List<String> groupNames = new ArrayList<>();
            if (st.hasMoreTokens()) {
                groupNames.addAll(Arrays.asList(st.nextToken().split(",")));
            }
            groupNames.add(userName);
            int userId = users.size();
            users.put(userName, userId);
            for (String groupName : groupNames) {
                if (!groups.containsKey(groupName)) {
                    int groupId = groups.size();
                    Group newGroup = new Group(groupId);
                    groups.put(groupName, groupId);
                    groupList.add(newGroup);
                }
                groupList.get(groups.get(groupName)).userIds.add(userId);
            }
        }

        while (F-- > 0) {
            st = new StringTokenizer(br.readLine());
            String fileName = st.nextToken();
            int mode = Integer.parseInt(st.nextToken());
            int ownerId = users.get(st.nextToken());
            int groupId = groups.get(st.nextToken());
            files.put(fileName, new File(mode, ownerId, groupId));
        }

        StringBuilder sb = new StringBuilder();
        Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int userId = users.get(st.nextToken());
            File file = files.get(st.nextToken());
            char operation = st.nextToken().charAt(0);
            sb.append(solution(userId, file, operation)).append("\n");
        }
        System.out.println(sb);
    }

    private static int solution(int userId, File file, char operation) {
        int temp = 0;
        int mask = operation == 'R' ? 4 : operation == 'W' ? 2 : 1;
        if (file.ownerId == userId) {
            temp |= file.mode / 100;
            temp |= file.mode / 10 % 10;
        } else if (groupList.get(file.groupId).userIds.contains(userId)) {
            temp |= file.mode / 10 % 10;
        }
        temp |= file.mode % 10;
        return (mask & temp) == 0 ? 0 : 1;
    }

    private static class File {
        int mode, ownerId, groupId;

        public File(int mode, int ownerId, int groupId) {
            this.mode = mode;
            this.ownerId = ownerId;
            this.groupId = groupId;
        }
    }

    private static class Group {
        int id;
        List<Integer> userIds;

        public Group(int id) {
            this.id = id;
            userIds = new ArrayList<>();
        }
    }
}