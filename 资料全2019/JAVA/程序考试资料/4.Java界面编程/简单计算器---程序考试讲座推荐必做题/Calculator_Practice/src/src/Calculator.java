package src;

public class Calculator
{
	private String result = "0";
	private int op = 0, add = 1, sub = 2, mul = 3, div = 4, sqrt = 5;

	private double stringToDouble(String s)
	{
		double y = Double.parseDouble(s);
		return y;
	}

	private void operate(String s)
	{
		double x = stringToDouble(s);
		double y = stringToDouble(result);

		switch (op)
		{
		case 0:
			result = s;
			break;
		case 1:
			result = String.valueOf(y + x);
			break;
		case 2:
			result = String.valueOf(y - x);
			break;
		case 3:
			result = String.valueOf(y * x);
			break;
		case 4:
			if (x != 0)
				result = String.valueOf(y / x);
			else
				result = "The divisior cannot be zero!";
			break;
		case 5:
			result = String.valueOf(Math.sqrt(x));
			break;
		case 6:
			result = "";
			break;
		}
	}

	public String opAdd(String s)
	{
		operate(s);
		op = add;
		return result;
	}

	public String opSubstract(String s)
	{
		operate(s);
		op = sub;
		return result;
	}

	public String opMultiply(String s)
	{
		operate(s);
		op = mul;
		return result;
	}

	public String opDivide(String s)
	{
		operate(s);
		op = div;
		return result;
	}

	public String opEqual(String s)
	{
		operate(s);
		op = 0;
		return result;
	}

	public String opSqrt(String s)
	{
		operate(s);
		op = sqrt;
		return result;
	}

	public void opClear()
	{
		op = 0;
		result = "0";
	}
}
