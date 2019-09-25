import { createReducer } from 'redux-starter-kit';
import { setEvents } from '../actions/events';

const initialState = {
  events: []
};

export default createReducer(initialState, {
  [setEvents]: (state, action) => ({ ...state, events: action.payload })
});
