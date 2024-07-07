import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import SwiftBic from './swift-bic';
import SwiftBicDetail from './swift-bic-detail';
import SwiftBicUpdate from './swift-bic-update';
import SwiftBicDeleteDialog from './swift-bic-delete-dialog';

const SwiftBicRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<SwiftBic />} />
    <Route path="new" element={<SwiftBicUpdate />} />
    <Route path=":id">
      <Route index element={<SwiftBicDetail />} />
      <Route path="edit" element={<SwiftBicUpdate />} />
      <Route path="delete" element={<SwiftBicDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default SwiftBicRoutes;
