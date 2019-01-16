
// LightView.h : CLightView 类的接口
//

#pragma once

#include "resource.h"
#include <vector>
using namespace std;

struct Position{
	int x;
	int y;
};

class CLightView : public CFormView
{
protected: // 仅从序列化创建
	CLightView();
	DECLARE_DYNCREATE(CLightView)

public:
	enum{ IDD = IDD_LIGHT_FORM };

// 特性
public:
	CLightDoc* GetDocument() const;

// 操作
public:

// 重写
public:
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // DDX/DDV 支持
	virtual void OnInitialUpdate(); // 构造后第一次调用

// 实现
public:
	virtual ~CLightView();
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
	enum CHESS{MIRROR_0,MIRROR_45,MIRROR_90,MIRROR_145,LIGHT,RED,BLANK};
	CHESS** chesses;
	int m;
	int n;
	int r;
	int align;
	int width;
	vector<Position>red_vector;
	void drawChessBoard();
	void drawChess();
	bool canPlace(int x,int y);
	void initChessBoard(int m,int n);
	afx_msg void OnBnClickedGenerateChess();
	afx_msg void OnBnClickedSave();
	afx_msg void OnBnClickedOpen();
	virtual void OnDraw(CDC* /*pDC*/);
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnBnClickedClear();
};

#ifndef _DEBUG  // LightView.cpp 中的调试版本
inline CLightDoc* CLightView::GetDocument() const
   { return reinterpret_cast<CLightDoc*>(m_pDocument); }
#endif

