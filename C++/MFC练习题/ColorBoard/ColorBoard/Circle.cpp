#include "StdAfx.h"
#include "Circle.h"

IMPLEMENT_SERIAL(Circle,CObject,VERSIONABLE_SCHEMA | 2)
Circle::Circle(void)
{
}


Circle::~Circle(void)
{
}

void Circle::Draw(CDC *pc){
	CPen pen(PS_SOLID,1,getPenColor());
	pc->SelectObject(pen);
	pc->Ellipse(start.x,start.y,end.x,end.y);
	pc->SelectStockObject(NULL_BRUSH);
}

void Circle::Serialize(CArchive& ar)
{
	Geometry::Serialize(ar);
	if (ar.IsStoring())
	{	// storing code
		ar<<start;
		ar<<end;
		ar<<pen_color;
	}
	else
	{	// loading code
		ar>>start;
		ar>>end;
		ar>>pen_color;
	}
}
