#pragma once
#include "geometry.h"
class Line :
	public Geometry
{
public:
	Line(void);
	~Line(void);

	void Draw(CDC *pc);
	DECLARE_SERIAL(Line);
	virtual void Serialize(CArchive& ar);
};

