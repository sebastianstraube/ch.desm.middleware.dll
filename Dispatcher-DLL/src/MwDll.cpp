#include <iostream>
#include <string>
#include "MwDll.h"

using namespace desm;

struct MwDll::Impl {

	// types

	typedef int (*t_stw_onStartProgramm)(char*);
	typedef int (*t_stw_onStopProgramm)(void);
	typedef int (*t_stw_setKilometerDirection)(int);
	typedef int (*t_stw_getKilometerDirection)(int*);

	// member

	HINSTANCE m_hInstLibrary;

	t_stw_onStartProgramm m_stw_onStartProgramm;
	t_stw_onStopProgramm m_stw_onStopProgramm;
	t_stw_setKilometerDirection m_stw_setKilometerDirection;
	t_stw_getKilometerDirection m_stw_getKilometerDirection;

	// lifetime

	Impl(LPCWSTR dllName)
		: m_hInstLibrary(NULL)
		, m_stw_onStartProgramm(NULL)
		, m_stw_onStopProgramm(NULL)
		, m_stw_setKilometerDirection(NULL)
	{
		m_hInstLibrary = LoadLibrary(dllName);
		if(!m_hInstLibrary) {
			throw std::exception("dll not found");
		}
		
		m_stw_onStartProgramm = (t_stw_onStartProgramm)GetProcAddress(m_hInstLibrary, "stw_onStartProgramm");
		if(!m_stw_onStartProgramm) {
			throw std::exception("stw_onStartProgramm not found");
		}
		
		m_stw_onStopProgramm = (t_stw_onStopProgramm)GetProcAddress(m_hInstLibrary, "stw_onStopProgramm");
		if(!m_stw_onStopProgramm) {
			throw std::exception("stw_onStopProgramm not found");
		}
		
		m_stw_setKilometerDirection = (t_stw_setKilometerDirection)GetProcAddress(m_hInstLibrary, "stw_setKilometerDirection");
		if(!m_stw_setKilometerDirection) {
			throw std::exception("stw_setKilometerDirection not found");
		}

		m_stw_getKilometerDirection = (t_stw_getKilometerDirection)GetProcAddress(m_hInstLibrary, "stw_getKilometerDirection");
		if(!m_stw_getKilometerDirection) {
			throw std::exception("stw_getKilometerDirection not found");
		}
	}

	~Impl() {
		FreeLibrary(m_hInstLibrary);
	}

};

MwDll::MwDll(LPCWSTR dllName)
	: m_pImpl(new MwDll::Impl(dllName))
{
}

MwDll::~MwDll() {
	delete m_pImpl;
}

int MwDll::stw_onStartProgramm(char* configPath) {
	return m_pImpl->m_stw_onStartProgramm(configPath);
}

int MwDll::stw_onStopProgramm() {
	return m_pImpl->m_stw_onStopProgramm();
}

int MwDll::stw_setKilometerDirection(int direction) {
	return m_pImpl->m_stw_setKilometerDirection(direction);
}

int MwDll::stw_getKilometerDirection(int& direction) {
	return m_pImpl->m_stw_getKilometerDirection(&direction);
}
