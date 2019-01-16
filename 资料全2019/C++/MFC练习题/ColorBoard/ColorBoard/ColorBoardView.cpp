
// ColorBoardView.cpp : CColorBoardView 类的实现
//

#include "stdafx.h"
// SHARED_HANDLERS 可以在实现预览、缩略图和搜索筛选器句柄的
// ATL 项目中进行定义，并允许与该项目共享文档代码。
#ifndef SHARED_HANDLERS
#include "ColorBoard.h"
#endif

#include "ColorBoardDoc.h"
#include "ColorBoardView.h"


#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CColorBoardView

IMPLEMENT_DYNCREATE(CColorBoardView, CView)

BEGIN_MESSAGE_MAP(CColorBoardView, CView)
	// 标准打印命令
	ON_COMMAND(ID_FILE_PRINT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_DIRECT, &CView::OnFilePrint)
	ON_COMMAND(ID_FILE_PRINT_PREVIEW, &CColorBoardView::OnFilePrintPreview)
	ON_WM_CONTEXTMENU()
	ON_WM_RBUTTONUP()
	ON_COMMAND(ID_LINE, &CColorBoardView::OnLine)
	ON_COMMAND(ID_CIRCLE, &CColorBoardView::OnCircle)
	ON_COMMAND(ID_RANCTANGLE, &CColorBoardView::OnRanctangle)
	ON_COMMAND(ID_COLOR, &CColorBoardView::OnColor)
	ON_WM_LBUTTONDOWN()
	ON_WM_LBUTTONUP()
	ON_WM_MOUSEMOVE()
	ON_COMMAND(ID_UNDO, &CColorBoardView::OnUndo)
	ON_COMMAND(ID_REDO, &CColorBoardView::OnRedo)
	ON_COMMAND(ID_CLEAR, &CColorBoardView::OnClear)
END_MESSAGE_MAP()

// CColorBoardView 构造/析构

CColorBoardView::CColorBoardView()
{
	// TODO: 在此处添加构造代码
	isMouseDown = false;
}

CColorBoardView::~CColorBoardView()
{
}

BOOL CColorBoardView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: 在此处通过修改
	//  CREATESTRUCT cs 来修改窗口类或样式

	return CView::PreCreateWindow(cs);
}

// CColorBoardView 绘制

void CColorBoardView::OnDraw(CDC* pDC)
{
	CColorBoardDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	CObArray& geometries = pDoc->geometries;
	for(int i = 0 ; i < geometries.GetCount() ; i++){
		Geometry* geometry = (Geometry*)geometries[i];
		geometry->Draw(pDC);
	}

	// TODO: 在此处为本机数据添加绘制代码
}


// CColorBoardView 打印


void CColorBoardView::OnFilePrintPreview()
{
#ifndef SHARED_HANDLERS
	AFXPrintPreview(this);
#endif
}

BOOL CColorBoardView::OnPreparePrinting(CPrintInfo* pInfo)
{
	// 默认准备
	return DoPreparePrinting(pInfo);
}

void CColorBoardView::OnBeginPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加额外的打印前进行的初始化过程
}

void CColorBoardView::OnEndPrinting(CDC* /*pDC*/, CPrintInfo* /*pInfo*/)
{
	// TODO: 添加打印后进行的清理过程
}

void CColorBoardView::OnRButtonUp(UINT /* nFlags */, CPoint point)
{
	ClientToScreen(&point);
	OnContextMenu(this, point);
}

void CColorBoardView::OnContextMenu(CWnd* /* pWnd */, CPoint point)
{
#ifndef SHARED_HANDLERS
	theApp.GetContextMenuManager()->ShowPopupMenu(IDR_POPUP_EDIT, point.x, point.y, this, TRUE);
#endif
}


// CColorBoardView 诊断

#ifdef _DEBUG
void CColorBoardView::AssertValid() const
{
	CView::AssertValid();
}

void CColorBoardView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

CColorBoardDoc* CColorBoardView::GetDocument() const // 非调试版本是内联的
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CColorBoardDoc)));
	return (CColorBoardDoc*)m_pDocument;
}
#endif //_DEBUG


// CColorBoardView 消息处理程序


void CColorBoardView::OnLine()
{
	// TODO: 在此添加命令处理程序代码
	curType = LINE;
}


void CColorBoardView::OnCircle()
{
	// TODO: 在此添加命令处理程序代码
	curType = CIRCLE;
}


void CColorBoardView::OnRanctangle()
{
	// TODO: 在此添加命令处理程序代码
	curType = RANTANGLE;
}


void CColorBoardView::OnColor()
{
	// TODO: 在此添加命令处理程序代码
	CColorDialog dig;
	if(dig.DoModal() == IDOK){
		curColor = dig.GetColor();
	}
}

void CColorBoardView::addGeometry(){

	CColorBoardDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	switch(curType){
	case LINE:
		curGeometry = new Line();
		pDoc->geometries.Add(curGeometry);
		break;
	case CIRCLE:
		curGeometry = new Circle();
		pDoc->geometries.Add(curGeometry);
		break;
	case RANTANGLE:
		curGeometry = new Rectan();
		pDoc->geometries.Add(curGeometry);
		break;
	}
}

void CColorBoardView::setCurGeometry(CPoint start,CPoint end){
	if(curGeometry){
		switch(curType){
		case LINE:
			((Line*)curGeometry)->start = start;
			((Line*)curGeometry)->end = end;
			((Line*)curGeometry)->pen_color = curColor;
			break;
		case CIRCLE:
			((Circle*)curGeometry)->start = start;
			((Circle*)curGeometry)->end = end;
			((Circle*)curGeometry)->pen_color = curColor;
			break;
		case RANTANGLE:
			((Rectan*)curGeometry)->start = start;
			((Rectan*)curGeometry)->end = end;
			((Rectan*)curGeometry)->pen_color = curColor;
		}
	}
	Invalidate(true);
}

void CColorBoardView::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	isMouseDown = true;
	start = point;
	addGeometry();
	CView::OnLButtonDown(nFlags, point);
}


void CColorBoardView::OnLButtonUp(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	isMouseDown = false;
	CView::OnLButtonUp(nFlags, point);
}


void CColorBoardView::OnMouseMove(UINT nFlags, CPoint point)
{
	// TODO: 在此添加消息处理程序代码和/或调用默认值
	if(isMouseDown){
		setCurGeometry(start,point);
	}

	CView::OnMouseMove(nFlags, point);
}


void CColorBoardView::OnUndo()
{
	// TODO: 在此添加命令处理程序代码
	CColorBoardDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	for(int i = 0 ; i < pDoc->geometries.GetCount() ; i++){
		geometries_copy.Add(pDoc->geometries.GetAt(i));
	}
	pDoc->geometries.RemoveAt(pDoc->geometries.GetCount()-1);
	Invalidate(TRUE);
}


void CColorBoardView::OnRedo()
{
	// TODO: 在此添加命令处理程序代码
	CColorBoardDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	pDoc->geometries.RemoveAll();
	for(int i = 0 ; i < geometries_copy.GetCount() ; i++){
		pDoc->geometries.Add(geometries_copy.GetAt(i));
	}
	Invalidate(TRUE);


}


void CColorBoardView::OnClear()
{
	// TODO: 在此添加命令处理程序代码
	CColorBoardDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	pDoc->geometries.RemoveAll();
	Invalidate(TRUE);
}
