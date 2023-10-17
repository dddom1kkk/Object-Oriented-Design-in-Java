package ca.cmpt213.as2;

public class Tokimon {
    public class Compatibility {
        private double score;
        private String comment;

        // Getters & Setters
        public double getScore() {
            return score;
        }

        public String getComment() {
            return comment;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }

    private String id;
    private String name;
    private Compatibility compatibility;

    // Getters & Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Compatibility getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(Compatibility compatibility) {
        this.compatibility = compatibility;
    }

}
