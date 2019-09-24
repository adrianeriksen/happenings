import React from 'react';
import { Link } from '@material-ui/core';
import CollisionLink from './CollisionLink';

function AnchorLink(props) {
  const { label, ...rest } = props;

  return (
    <Link component={CollisionLink} {...rest}>
      {label}
    </Link>
  );
}

export default AnchorLink;
