#include "StdAfx.h"
#include "Geometry.h"

IMPLEMENT_DYNAMIC(Geometry,CObject)
Geometry::Geometry(void)
{
}


Geometry::~Geometry(void)
{
}

void Geometry::setPenColor(COLORREF color){
	pen_color = color;
}
COLORREF Geometry::getPenColor(){
	return pen_color;
}


void Geometry::Serialize(CArchive& ar)
{
	CObject::Serialize(ar);
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
