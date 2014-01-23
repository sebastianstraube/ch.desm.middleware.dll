#pragma once

#include <json/json.h>

namespace desm {
	namespace util {

		template<class TContainer> static Json::Value stlContainerToJsonArray(const TContainer& container) {
			Json::Value res = Json::Value(Json::arrayValue);
			TContainer::const_iterator it = container.begin();
			for(; it != container.end(); ++it) {
				res.append(Json::Value(*it));
			}
			return res;
		}

		template <class TContainer> static TContainer jsonArrayToStlContainer(const Json::Value& v) {
			TContainer res;
			for(Json::Value::iterator it = v.begin(); it != v.end(); ++it) {
				res.push_back(jsonGetAs<TContainer::value_type>(*it));
			}
			return res;
		}

		template<class T> static T jsonGetAs(const Json::Value& v);
		template<> static int jsonGetAs<int>(const Json::Value& v) { return v.asInt(); }
		template<> static double jsonGetAs<double>(const Json::Value& v) { return v.asDouble(); }
		template<> static unsigned jsonGetAs<unsigned>(const Json::Value& v) { return v.asUInt(); }
		template<> static std::string jsonGetAs<std::string>(const Json::Value& v) { return v.asString(); }
		template<> static std::vector<int> jsonGetAs<std::vector<int>>(const Json::Value& v) { return jsonArrayToStlContainer<std::vector<int>>(v); }
		template<> static std::vector<double> jsonGetAs<std::vector<double>>(const Json::Value& v) { return jsonArrayToStlContainer<std::vector<double>>(v); }

		template<class T> static bool jsonIsOfType(const Json::Value& v);
		template<> static bool jsonIsOfType<int>(const Json::Value& v) { return v.isInt(); }
		template<> static bool jsonIsOfType<double>(const Json::Value& v) { return v.isDouble(); }
		template<> static bool jsonIsOfType<unsigned>(const Json::Value& v) { return v.isUInt(); }
		template<> static bool jsonIsOfType<std::string>(const Json::Value& v) { return v.isString(); }
		template<> static bool jsonIsOfType<std::vector<int>>(const Json::Value& v) { return v.isArray(); }
		template<> static bool jsonIsOfType<std::vector<double>>(const Json::Value& v) { return v.isArray(); }

		template<class T> static T jsonGet(const Json::Value& v, const char* key) {
			if(!v.isObject()) {
				throw std::exception("invalid object provided for parsing");
			}
			Json::Value x = v.get(key, Json::Value::null);
			if(!jsonIsOfType<T>(x) || x.isNull()) {
				throw std::exception("json parser error");
			}
			return jsonGetAs<T>(x);
		}

	}
};