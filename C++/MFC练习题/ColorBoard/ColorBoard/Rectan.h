#pragma once
#include "geometry.h"
class Rectan :
	public Geometry
{
public:
	Rectan(void);
	~Rectan(void);

	void Draw(CDC *pc);
	DECLARE_SERIAL(Rectan);
	virtual void Serialize(CArchive& ar);
};

