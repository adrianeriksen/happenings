import { createReducer } from 'redux-starter-kit';
import { setEvent, setEvents } from '../actions/events';

const initialState = {
  event: null,
  events: []
};

export default createReducer(initialState, {
  [setEvent]: (state, action) => ({ ...state, event: action.payload }),
  [setEvents]: (state, action) => ({ ...state, events: action.payload })
});
