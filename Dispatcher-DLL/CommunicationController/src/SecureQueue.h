#pragma once

#include <queue>
#include "CriticalSection.h"

namespace desm {
	
	template<typename T>
	class SecureQueue {
	public: // lifetime
		SecureQueue() : m_queue(), m_csh() {}
		~SecureQueue() {}
	public: // access
		void clear() {
			CriticalSection cs(m_csh);
			while(!m_queue.empty()) {
				m_queue.pop();
			}
		}
		void push(const T& data) {
			CriticalSection cs(m_csh);
			m_queue.push(data);
		}
		bool pop(T& data) {
			CriticalSection cs(m_csh);
			if(m_queue.empty()) {
				return false;
			}
			data = m_queue.front();
			m_queue.pop();
			return true;
		}
	private: // member
		std::queue<typename T> m_queue;
		CriticalSectionHandle m_csh;
	};

};