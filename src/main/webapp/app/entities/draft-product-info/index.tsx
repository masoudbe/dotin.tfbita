import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DraftProductInfo from './draft-product-info';
import DraftProductInfoDetail from './draft-product-info-detail';
import DraftProductInfoUpdate from './draft-product-info-update';
import DraftProductInfoDeleteDialog from './draft-product-info-delete-dialog';

const DraftProductInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DraftProductInfo />} />
    <Route path="new" element={<DraftProductInfoUpdate />} />
    <Route path=":id">
      <Route index element={<DraftProductInfoDetail />} />
      <Route path="edit" element={<DraftProductInfoUpdate />} />
      <Route path="delete" element={<DraftProductInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DraftProductInfoRoutes;
