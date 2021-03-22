package grammer.stream;

/**
 * @author taoruizhe
 * @detail 模拟流式API或链式调用
 * @date 20191220
 */

public class StreamAPI {
    private int id;
    private String name;
    private String date;

    public Builder builder() {
        return new Builder();
    }

    class Builder {
        private int id;
        private String name;
        private String date;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public StreamAPI build() {
            StreamAPI stream = new StreamAPI();
            stream.id = this.id;
            stream.name = this.name;
            stream.date = this.date;
            return stream;

        }
    }

    public static void main(String[] args) {
        StreamAPI stream = new StreamAPI().builder()
                .setId(1)
                .setName("taylor")
                .setDate("now")
                .build();
        System.out.println(stream.id);

    }
}
