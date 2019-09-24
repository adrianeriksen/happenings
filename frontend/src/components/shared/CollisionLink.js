import React, { forwardRef } from 'react';
import { Link as RouterLink } from 'react-router-dom';

const CollisionLink = forwardRef((props, ref) => (
  <RouterLink innerRef={ref} {...props} />
));

export default CollisionLink;
