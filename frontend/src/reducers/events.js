import { createReducer } from 'redux-starter-kit';
import {
  fetchEventInitiatedAction,
  fetchEventSuccessAction,
  fetchEventErrorAction,
  fetchEventsInitiatedAction,
  fetchEventsSuccessAction,
  fetchEventsErrorAction,
  createEventInitiatedAction,
  createEventSuccessAction,
  createEventErrorAction
} from '../actions/events';

const initialState = {
  isFetching: false,
  isSubmitting: false,
  event: null,
  events: []
};

export default createReducer(initialState, {
  [createEventInitiatedAction]: state => ({
    ...state,
    isSubmitting: true
  }),
  [createEventSuccessAction]: state => ({
    ...state,
    isSubmitting: false
  }),
  [createEventErrorAction]: state => ({
    ...state,
    isSubmitting: false
  }),
  [fetchEventInitiatedAction]: state => ({
    ...state,
    event: null,
    isFetching: true
  }),
  [fetchEventSuccessAction]: (state, action) => ({
    ...state,
    event: action.payload,
    isFetching: false
  }),
  [fetchEventErrorAction]: state => ({
    ...state,
    isFetching: false
  }),
  [fetchEventsInitiatedAction]: state => ({
    ...state,
    events: [],
    isFetching: true
  }),
  [fetchEventsSuccessAction]: (state, action) => ({
    ...state,
    events: action.payload,
    isFetching: false
  }),
  [fetchEventsErrorAction]: state => ({
    ...state,
    isFetching: false
  })
});
