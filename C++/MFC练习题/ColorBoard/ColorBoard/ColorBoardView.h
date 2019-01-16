
// ColorBoardView.h : CColorBoardView 类的接口
//

#pragma once


class CColorBoardView : public CView
{
protected: // 仅从序列化创建
	CColorBoardView();
	DECLARE_DYNCREATE(CColorBoardView)

// 特性
public:
	CColorBoardDoc* GetDocument() const;

// 操作
public:
	enum GeomType{LINE,CIRCLE,RANTANGLE};
	COLORREF curColor;
	GeomType curType;
	CObject *curGeometry;
	CPoint start;
	bool isMouseDown;
	CObArray geometries_copy;
// 重写
public:
	virtual void OnDraw(CDC* pDC);  // 重写以绘制该视图
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
	void addGeometry();
	void setCurGeometry(CPoint start,CPoint end);
protected:
	virtual BOOL OnPreparePrinting(CPrintInfo* pInfo);
	virtual void OnBeginPrinting(CDC* pDC, CPrintInfo* pInfo);
	virtual void OnEndPrinting(CDC* pDC, CPrintInfo* pInfo);

// 实现
public:
	virtual ~CColorBoardView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// 生成的消息映射函数
protected:
	afx_msg void OnFilePrintPreview();
	afx_msg void OnRButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnContextMenu(CWnd* pWnd, CPoint point);
	DECLARE_MESSAGE_MAP()
public:
	afx_msg void OnLine();
	afx_msg void OnCircle();
	afx_msg void OnRanctangle();
	afx_msg void OnColor();
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnLButtonUp(UINT nFlags, CPoint point);
	afx_msg void OnMouseMove(UINT nFlags, CPoint point);
	afx_msg void OnUndo();
	afx_msg void OnRedo();
	afx_msg void OnClear();
};

#ifndef _DEBUG  // ColorBoardView.cpp 中的调试版本
inline CColorBoardDoc* CColorBoardView::GetDocument() const
   { return reinterpret_cast<CColorBoardDoc*>(m_pDocument); }
#endif

