#pragma once
#include "geometry.h"
class Circle :
	public Geometry
{
public:
	Circle(void);
	~Circle(void);

	void Draw(CDC *pc);
	DECLARE_SERIAL(Circle);
	virtual void Serialize(CArchive& ar);
};

