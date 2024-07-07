import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ObjectiveCategory from './objective-category';
import ObjectiveCategoryDetail from './objective-category-detail';
import ObjectiveCategoryUpdate from './objective-category-update';
import ObjectiveCategoryDeleteDialog from './objective-category-delete-dialog';

const ObjectiveCategoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ObjectiveCategory />} />
    <Route path="new" element={<ObjectiveCategoryUpdate />} />
    <Route path=":id">
      <Route index element={<ObjectiveCategoryDetail />} />
      <Route path="edit" element={<ObjectiveCategoryUpdate />} />
      <Route path="delete" element={<ObjectiveCategoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ObjectiveCategoryRoutes;
