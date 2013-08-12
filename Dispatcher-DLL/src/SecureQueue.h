#pragma once

#include <list>
#include "CriticalSection.h"
#include "Thread.h"

namespace desm {

	template<typename T>
	class SecureQueue {
	private: // types
		typedef T                       tData;
		typedef SecureQueue<typename T> tThis;
		typedef std::list<typename T>   tQueue;
	public: // lifetime
		SecureQueue() : m_queue(), m_csh() {}
		~SecureQueue() {}
	public: // access
		void clear() {
			CriticalSection cs(m_csh);
			m_queue.clear();
		}
		void push(const T& data) {
			CriticalSection cs(m_csh);
			m_queue.push_back(data);
		}
		bool pop(T& data) {
			CriticalSection cs(m_csh);
			if(m_queue.empty()) {
				return false;
			}
			data = m_queue.front();
			m_queue.pop_front();
			return true;
		}
		void copyTo(tThis& q) {
			CriticalSection cs(m_csh);
			tQueue::const_iterator it = m_queue.begin();
			for(; it != m_queue.end(); ++it) {
				q.push(*it);
			}
		}
		void moveTo(tThis& q) {
			CriticalSection cs(m_csh);
			moveToImpl(q.m_queue);
		}
	private: // helper
		void moveToImpl(tQueue& q) {
			while(!m_queue.empty()) {
				tData data = m_queue.front();
				m_queue.pop_front();
				q.push_back(data);
			}
		}
	private: // member
		tQueue m_queue;
		CriticalSectionHandle m_csh;
	};

};