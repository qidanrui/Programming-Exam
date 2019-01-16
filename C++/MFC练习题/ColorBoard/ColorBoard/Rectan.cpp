#include "StdAfx.h"
#include "Rectan.h"

IMPLEMENT_SERIAL(Rectan,CObArray,VERSIONABLE_SCHEMA | 2)
Rectan::Rectan(void)
{
}


Rectan::~Rectan(void)
{
}

void Rectan::Draw(CDC *pc){
	CPen pen(PS_SOLID,1,getPenColor());
	pc->SelectObject(pen);
	pc->Rectangle(start.x,start.y,end.x,end.y);
	pc->SelectStockObject(NULL_BRUSH);
}


void Rectan::Serialize(CArchive& ar)
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
