package ca.cmpt213.as2;

public class Tokimon {
    private class Compatibility {
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
            if (score < 0) {
                System.out.println("Error: Invalid score");
                System.exit(-1);
            }
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

    public Compatibility getCompatibility() {
        return compatibility;
    }

    public double getScore() {
        return compatibility.getScore();
    }

    public String getComment() {
        return compatibility.getComment();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompatibility(Compatibility compatibility) {
        this.compatibility = compatibility;
    }

    public void setScore(double score) {
        compatibility.setScore(score);
    }

    public void setComment(String comment) {
        compatibility.setComment(comment);
    }

}
