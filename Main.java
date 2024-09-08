import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Group {
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Stream implements Iterable<Group> {
    private List<Group> groups;

    public Stream(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    @Override
    public Iterator<Group> iterator() {
        return groups.iterator();
    }
}

class StreamComparator {
    public int compare(Stream s1, Stream s2) {
        return s1.getGroups().size() - s2.getGroups().size();
    }
}

class StreamService {
    public void sortStreams(List<Stream> streams) {
        streams.sort(new StreamComparator()::compare);
    }
}

class Controller {
    private StreamService streamService;

    public Controller(StreamService streamService) {
        this.streamService = streamService;
    }

    public void sortStreams(List<Stream> streams) {
        streamService.sortStreams(streams);
    }
}

public class Main {
    public static void main(String[] args) {
        List<Group> groups1 = new ArrayList<>();
        groups1.add(new Group("Group 1"));
        groups1.add(new Group("Group 2"));

        List<Group> groups2 = new ArrayList<>();
        groups2.add(new Group("Group 3"));
        groups2.add(new Group("Group 4"));
        groups2.add(new Group("Group 5"));

        Stream stream1 = new Stream(groups1);
        Stream stream2 = new Stream(groups2);

        List<Stream> streams = new ArrayList<>();
        streams.add(stream1);
        streams.add(stream2);

        StreamService streamService = new StreamService();
        Controller controller = new Controller(streamService);

        controller.sortStreams(streams);

        for (Stream stream : streams) {
            System.out.println("Stream with " + stream.getGroups().size() + " groups:");
            for (Group group : stream) {
                System.out.println(group.getName());
            }
        }
    }
}
