#pragma once

namespace desm {

	enum eEvent {
		EVT_TRACK = 1,
		EVT_TRACK_CONNECTION,
		EVT_KILOMETER_DIRECTION,
		EVT_BALISE,
		EVT_ISOLIERSTOSS
	};

	typedef struct {
		eEvent event;
		int id1;
		int id2;
	} tChange;

};