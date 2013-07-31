#pragma once

#include <Windows.h>

namespace desm {

	class CriticalSectionHandle {
		public:
			CriticalSectionHandle() {
				InitializeCriticalSection(&m_cs);
			}

			~CriticalSectionHandle() {
				DeleteCriticalSection(&m_cs);
			}

			void enter() {
				EnterCriticalSection(&m_cs);
			}

			bool tryEnter() {
				return (TryEnterCriticalSection(&m_cs) != 0) ? true : false;
			}

			void leave() {
				LeaveCriticalSection(&m_cs);
			}

		private:
			CRITICAL_SECTION m_cs;
		};

	class CriticalSection {

		public:
			CriticalSectionHandle& m_csh;
			CriticalSection(CriticalSectionHandle& csh) : m_csh(csh) {
				m_csh.enter();
			}
			~CriticalSection() {
				m_csh.leave();
			}
	};

};