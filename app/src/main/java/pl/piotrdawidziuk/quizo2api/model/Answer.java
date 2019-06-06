package pl.piotrdawidziuk.quizo2api.model;

class Answer {

    Integer order;
    String text;
    Integer isCorrect;

    public Answer(Integer order, String text, Integer isCorrect) {
        this.order = order;
        this.text = text;
        this.isCorrect = isCorrect;
    }


    public Integer getOrder() {
        return order;
    }

    public String getText() {
        return text;
    }

    public Integer getIsCorrect() {
        return isCorrect;
    }
}
