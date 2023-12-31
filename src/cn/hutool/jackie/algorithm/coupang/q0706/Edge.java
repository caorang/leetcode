package cn.hutool.jackie.algorithm.coupang.q0706;

public class Edge {
    private Shape shape;
    private String code; // used to mock how pieces would fit together.
    private Piece parentPiece;

    public Edge(Shape shape, String code) {
        this.shape = shape;
        this.code = code;
    }

    private String getCode() {
        return code;
    }

    public Edge _createMatchingEdge() {
        if (shape == Shape.FLAT) {
            return null;
        }
        return new Edge(shape.getOpposite(), getCode());
    }

    /* Check if this edge fits into the other one. */
    public boolean fitsWith(Edge edge) {
        return edge.getCode().equals(getCode());
    }

    /* Get the parent piece. */
    public Piece getParentPiece() {
        return parentPiece;
    }

    /* Set parent piece. */
    public void setParentPiece(Piece parentPiece) {
        this.parentPiece = parentPiece;
    }

    /* Return the shape of the edge. */
    public Shape getShape() {
        return shape;
    }

    public String toString() {
        return code;
    }
}
