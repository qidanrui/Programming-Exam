#pragma once
#include "afx.h"
class Geometry :
	public CObject
{
public:
	CPoint start;
	CPoint end;
	COLORREF pen_color;
public:
	Geometry(void);
	virtual ~Geometry(void);

	void setPenColor(COLORREF color);
	COLORREF getPenColor();
	virtual void Draw(CDC *pc)=0;
	DECLARE_DYNAMIC(Geometry);
	virtual void Serialize(CArchive& ar);
};

