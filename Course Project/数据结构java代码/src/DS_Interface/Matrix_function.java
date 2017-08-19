package DS_Interface;

import DS_Class.Matrix;

public interface Matrix_function {
    public void Insert(Matrix matrix);
    public void Delete(Matrix matrix);
    public void Display(Matrix matrix);
    public void Display_R_D(Matrix matrix);
    public void Multiply(Matrix matrix);
    public void ChangeValue(Matrix matrix);
    public void Plus_Matrix(Matrix first,Matrix second);
    public void Multiply_Matrix(Matrix first,Matrix second);
}
