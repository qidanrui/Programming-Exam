#include "StdAfx.h"
#include "Line.h"

IMPLEMENT_SERIAL(Line,CObject,VERSIONABLE_SCHEMA | 2)
Line::Line(void)
{
}


Line::~Line(void)
{
}

void Line::Draw(CDC *pc){
	CPen pen(PS_SOLID,1,getPenColor());
	pc->SelectObject(pen);
	pc->MoveTo(start);
	pc->LineTo(end);
}

void Line::Serialize(CArchive& ar)
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
